package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.AnswerDto;
import hr.tvz.quizzard.model.Answer;
import hr.tvz.quizzard.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@PreAuthorize("hasAnyAuthority('admin', 'moderator', 'user')")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Integer id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Object> updateAnswer(@PathVariable Integer id, @RequestBody AnswerDto answerDto) {
        try {
            Answer savedAnswer = answerService.updateAnswer(id, answerDto);
            return ResponseEntity.ok(savedAnswer);
        } catch (Exception e) {
            //return ResponseHelper.errorMsgResponse(e.getMessage());
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


}
