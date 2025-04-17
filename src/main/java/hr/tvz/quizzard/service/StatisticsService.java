package hr.tvz.quizzard.service;

import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.repository.QuestionRepository;
import hr.tvz.quizzard.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public long getTotalNumberOfQuizzes() {
        return quizRepository.count();
    }

    public long getTotalNumberOfQuestions() {
        return questionRepository.count();
    }

    public double getAverageRatingOfAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .mapToDouble(quiz -> quiz.getAverageRating() != null ? quiz.getAverageRating() : 0)
                .average()
                .orElse(0.0);
    }

    public int getNumberOfQuestionsForQuiz(Integer quizId) {
        return questionRepository.findByQuizId_Id(quizId).size();
    }

    public String getMostPopularQuizCategory() {
        return quizRepository.findAll()
                .stream()
                .filter(quiz -> quiz.getCategory() != null)
                .map(quiz -> quiz.getCategory().toString())
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No category available");
    }

    public List<Quiz> getQuizzesByUser(Integer userId) {
        return quizRepository.findByOwnerId_Id(userId);
    }

    public int getTotalNumberOfAnswersForQuestion(Integer questionId) {
        return questionRepository.findById(questionId)
                .map(question -> question.getAnswers().size())
                .orElse(0);
    }

}
