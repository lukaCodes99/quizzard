package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.AnswerDto;
import hr.tvz.quizzard.mapper.AnswerMapper;
import hr.tvz.quizzard.model.Answer;
import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.repository.AnswerRepository;
import hr.tvz.quizzard.repository.QuestionRepository;
import hr.tvz.quizzard.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {

    private final UserEntityRepository userEntityRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;


    public Answer updateAnswer(Integer id, AnswerDto answerDto) {
        Answer exsistingAnswer = answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        Answer answerToSave = answerMapper.mapAnswerDtoToAnswer(answerDto);
        answerToSave.setId(id);
        answerToSave.setQuestionId(exsistingAnswer.getQuestionId());
        return answerRepository.save(answerToSave);
    }

    @Transactional
    public Answer saveAnswer(Integer questionId, AnswerDto answer) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        Answer answerToSave = answerMapper.mapAnswerDtoToAnswer(answer);
        answerToSave.setQuestionId(question);
        return answerRepository.save(answerToSave);
    }

    public void deleteAnswer(Integer answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        answerRepository.delete(answer);
    }

    public Answer getAnswerById(Integer id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
    }
}
