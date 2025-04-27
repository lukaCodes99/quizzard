package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.NewQuizDto;
import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.filterParams.QuizFilterParams;
import hr.tvz.quizzard.mapper.QuizMapper;
import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.model.Result;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.repository.QuizRepository;
import hr.tvz.quizzard.repository.ResultRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final ResultRepository resultRepository;

    public void addRating(Integer id, Double score) {
        if (score < 0 || score > 5) {
            throw new RuntimeException("Score must be between 0 and 5");
        }
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        Double newAverageRating = ((quiz.getAverageRating() * quiz.getRatingCount() + score) / (quiz.getRatingCount() + 1));
        quiz.setAverageRating(newAverageRating);
        quiz.setRatingCount(quiz.getRatingCount() + 1);
        quizRepository.save(quiz);
    }


    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }

    public List<QuizDto> getAllQuizzes() {

        return quizRepository.findAll().stream()
                .map(quizMapper::mapQuizToQuizDto)
                .toList();

    }

    @Transactional
    public void saveQuiz(NewQuizDto quizDto) {
        Quiz quiz = quizMapper.mapNewQuizDtoToQuiz(quizDto);
        quiz.setRatingCount(0);
        quiz.setAverageRating(0.0);
        quiz.setCreationDate(LocalDate.now());
        quizRepository.save(quiz);
    }

    public Page<QuizDto> getAllQuizzesFiltered(QuizFilterParams quizFilterParams, Pageable pageable) {
        return quizRepository.findAllFiltered(
                quizFilterParams.getCategory(),
                quizFilterParams.getAverageRatingFrom(),
                quizFilterParams.getAverageRatingTo(),
                quizFilterParams.getCreationDateFrom(),
                quizFilterParams.getCreationDateTo(),
                quizFilterParams.getDescription(),
                quizFilterParams.getTitle(),
                pageable
        ).map(quizMapper::mapQuizToQuizDto);
    }

    public Result solveQuiz(Integer id, Map<Integer, String> answers) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
        AtomicInteger correctAnswersCount = new AtomicInteger(0);

        quiz.getQuestions().forEach(question -> {
            String answer = answers.get(question.getId());
            if (answer != null) {
                boolean correct = question.getAnswers().stream()
                        .anyMatch(a -> (a.isCorrect() && a.getText().equals(answer)));
                if (correct) correctAnswersCount.incrementAndGet();
            }
        });

        Double score = (double) correctAnswersCount.get() / quiz.getQuestions().size();
        UserEntity userEntity = null;
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return resultRepository.save(new Result(userEntity, quiz, LocalDate.now(), score));
    }
}
