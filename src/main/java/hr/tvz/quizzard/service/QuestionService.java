package hr.tvz.quizzard.service;

import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question updateQuestion(Integer id, Question question) {
        questionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Question not found"));

        return questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Question not found"));

        questionRepository.deleteById(id);
    }
}
