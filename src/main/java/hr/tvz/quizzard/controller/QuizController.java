package hr.tvz.quizzard.controller;

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

    private final List<UserEntity> userEntityList = Arrays.asList(
            new UserEntity(1, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(2, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(3, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(4, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(5, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(6, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(7, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(8, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(9, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(10, "lzka", "sdad", "fdasda", Role.USER)
    );

    @GetMapping()
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userEntityList);
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


}
