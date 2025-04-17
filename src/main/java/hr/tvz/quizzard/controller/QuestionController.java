package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.AnswerDto;
import hr.tvz.quizzard.model.Answer;
import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.service.AnswerService;
import hr.tvz.quizzard.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(
            @PathVariable Integer id
    ) {
        try {
            Question question = questionService.getQuestionById(id);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateQuestion(
            @PathVariable Integer id,
            @RequestBody Question question
    ) {
        try {
            Question updatedQuestion = questionService.updateQuestion(id, question);
            return ResponseEntity.ok(updatedQuestion);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable Integer id
    ) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.ok("Question deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/add-answer")
    public ResponseEntity<?> addAnswer(
            @PathVariable Integer id,
            @RequestBody AnswerDto answer
    ) {
        try {
            Answer updatedAnswer = answerService.saveAnswer(id, answer);
            return ResponseEntity.ok(updatedAnswer);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
