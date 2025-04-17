package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.QuestionDto;
import hr.tvz.quizzard.mapper.QuestionMapper;
import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.model.Quiz;
import hr.tvz.quizzard.repository.QuestionRepository;
import hr.tvz.quizzard.repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuizRepository quizRepository;

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Question not found"));
    }

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

    @Transactional
    public Question saveQuestion(Integer quizId, QuestionDto questionDto) {
        Quiz quiz = quizRepository.findById(quizId)
                        .orElseThrow(() -> new RuntimeException("Quiz not found"));
        Question question = questionMapper.mapQuestionDtoToQuestion(questionDto);
        question.setQuizId(quiz);
        if (question.getAnswers() != null) {
            question.getAnswers().forEach(answer -> answer.setQuestionId(question));
        }

        return questionRepository.save(question);
    }
}
