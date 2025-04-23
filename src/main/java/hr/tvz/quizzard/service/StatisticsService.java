package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.*;
import hr.tvz.quizzard.model.*;
import hr.tvz.quizzard.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StatisticsService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ResultRepository resultRepository;
    private final UserEntityRepository userRepository;

    public PlatformStatisticsDto getPlatformStatistics() {
        return new PlatformStatisticsDto(
                userRepository.count(),
                quizRepository.count(),
                questionRepository.count(),
                answerRepository.count(),
                resultRepository.count()
        );
    }

    public List<QuizStatisticsDto> getTopQuizzesByAttempts(int limit) {
        return resultRepository.findTopQuizzesByAttempts(PageRequest.of(0, limit));
    }

    public List<QuizStatisticsDto> getTopQuizzesByRating(int limit) {
        return quizRepository.findTopByOrderByAverageRatingDesc().stream()
                .limit(limit)
                .map(q -> new QuizStatisticsDto(
                        q.getId(),
                        q.getTitle(),
                        getAverageScoreForQuiz(q.getId()),
                        getTotalAttemptsForQuiz(q.getId()),
                        q.getAverageRating()))
                .collect(Collectors.toList());
    }

    public double getAverageScoreForQuiz(Integer quizId) {
        return resultRepository.findAverageScoreForQuiz(quizId);
    }

    public long getTotalAttemptsForQuiz(Integer quizId) {
        return resultRepository.countByQuizId_Id(quizId);
    }

    public List<CategoryStatsDto> getStatsPerCategory() {
        return resultRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getQuizId().getCategory(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    long attempts = list.size();
                                    double avgScore = list.stream().mapToDouble(Result::getScore).average().orElse(0);
                                    return new CategoryStatsDto(list.getFirst().getQuizId().getCategory(), attempts, avgScore);
                                }
                        )
                ))
                .values().stream().toList();
    }

    public Map<LocalDate, Long> getAttemptTrends(LocalDate from, LocalDate to) {
        return resultRepository.findByDateBetween(from, to).stream()
                .collect(Collectors.groupingBy(Result::getDate, Collectors.counting()));
    }

    public Map<String, Long> getScoreDistribution(Integer quizId) {
        List<Result> results = resultRepository.findByQuizId_Id(quizId);

        Map<String, Long> distribution = new LinkedHashMap<>();
        distribution.put("0–50%", 0L);
        distribution.put("51–75%", 0L);
        distribution.put("76–90%", 0L);
        distribution.put("91–100%", 0L);

        for (Result r : results) {
            double s = r.getScore();
            if (s <= 50) distribution.put("0–50%", distribution.get("0–50%") + 1);
            else if (s <= 75) distribution.put("51–75%", distribution.get("51–75%") + 1);
            else if (s <= 90) distribution.put("76–90%", distribution.get("76–90%") + 1);
            else distribution.put("91–100%", distribution.get("91–100%") + 1);
        }

        return distribution;
    }
}
