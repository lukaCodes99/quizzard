package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.NewQuizDto;
import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.model.Role;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.service.QuizService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable Integer id) {
        try {
            Quiz quiz = quizService.getQuizById(id);
            return ResponseEntity.ok(quiz);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        try {
            List<QuizDto> quizzes = quizService.getAllQuizzes();
            return ResponseEntity.ok(quizzes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/{id}/add-rating")
    public ResponseEntity<?> addRating(
            @PathVariable Integer id,
            @RequestBody Double score
    ) {
        try {
            quizService.addRating(id, score);
            return ResponseEntity.ok("Rating added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveQuiz(@RequestBody NewQuizDto quizDto) {
        try {
            quizService.saveQuiz(quizDto);
            return ResponseEntity.ok("Quiz saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
