package hr.tvz.quizzard.mapper;

import hr.tvz.quizzard.dto.AnswerDto;
import hr.tvz.quizzard.model.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    private final ModelMapper modelMapper;

    public AnswerMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Answer mapAnswerDtoToAnswer(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }

    public AnswerDto mapAnswerToAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

}
