package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.NewQuizDto;
import hr.tvz.quizzard.dto.QuestionDto;
import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.dto.ResultDto;
import hr.tvz.quizzard.filterParams.QuizFilterParams;
import hr.tvz.quizzard.helpers.PageableHelper;
import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.model.Result;
import hr.tvz.quizzard.service.QuestionService;
import hr.tvz.quizzard.service.QuizService;
import hr.tvz.quizzard.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final ResultService resultService;

    public QuizController(QuizService quizService, QuestionService questionService, ResultService resultService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.resultService = resultService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<Map<String, Object>> getAllQuizzes(
            @ModelAttribute QuizFilterParams quizFilterParams,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") Boolean desc
    ) {
        try {
            Page<QuizDto> quizzesPaged = quizService.getAllQuizzesFiltered(quizFilterParams,
                    PageableHelper.getPageableObject(pageIndex, pageSize, sortField, desc));

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("data", quizzesPaged.getContent());
            response.put("pageIndex", quizzesPaged.getNumber());
            response.put("rowCount", quizzesPaged.getTotalElements());
            response.put("pageSize", quizzesPaged.getSize());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{id}/solve")
    public ResponseEntity<?> solveQuiz(
            @PathVariable Integer id,
            @RequestBody Map<Integer, String> answers
    ) {
        try {
            Result result = quizService.solveQuiz(id, answers);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/add-rating")
    @PreAuthorize("hasAnyAuthority('admin', 'moderator', 'user')")
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
    @PreAuthorize("hasAnyAuthority('admin', 'moderator', 'user')")
    public ResponseEntity<?> saveQuiz(@RequestBody NewQuizDto quizDto) {
        try {
            quizService.saveQuiz(quizDto);
            return ResponseEntity.ok("Quiz saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/add-question")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<?> saveQuestion(
            @PathVariable Integer id,
            @RequestBody QuestionDto questionDto
    ) {
        try {
            Question savedQuestion = questionService.saveQuestion(id, questionDto);
            return ResponseEntity.ok(savedQuestion);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/my-results")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<?> getMyResults(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") Boolean desc
    ) {
        try {
            Page<ResultDto> resultsPaged = resultService.getMyResults(userId, PageableHelper.getPageableObject(pageIndex, pageSize, sortField, desc));

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("data", resultsPaged.getContent());
            response.put("pageIndex", resultsPaged.getNumber());
            response.put("rowCount", resultsPaged.getTotalElements());
            response.put("pageSize", resultsPaged.getSize());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
