package hr.tvz.quizzard.mapper;

import hr.tvz.quizzard.dto.AnswerDto;
import hr.tvz.quizzard.dto.QuestionDto;
import hr.tvz.quizzard.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;
    private final AnswerMapper answerMapper;

    public QuestionMapper(AnswerMapper answerMapper) {
        this.modelMapper = new ModelMapper();
        this.answerMapper = answerMapper;
    }

    public QuestionDto mapQuestionToQuestionDto(Question question) {

        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setAnswers(
                question.getAnswers().stream()
                        .map(answerMapper::mapAnswerToAnswerDto)
                        .toList()
        );
        return questionDto;
    }


    public Question mapQuestionDtoToQuestion(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        question.setAnswers(
                questionDto.getAnswers().stream()
                        .map(answerMapper::mapAnswerDtoToAnswer)
                        .toList()
        );
        return question;
    }

}
