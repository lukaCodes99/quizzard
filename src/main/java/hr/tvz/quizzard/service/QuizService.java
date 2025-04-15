package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.mapper.QuizMapper;
import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

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
        List<Quiz> quizzes = quizRepository.findAll();
//        return quizzes.stream()
//                .map(quizMapper::mapQuizToQuizDto)
//                .toList();
        List<QuizDto> quizDtos = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            System.out.println(quiz.toString());
            quizDtos.add(quizMapper.mapQuizToQuizDto(quiz));
        }
        return quizDtos;
    }
}
