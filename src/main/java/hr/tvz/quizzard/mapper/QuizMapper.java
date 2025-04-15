package hr.tvz.quizzard.mapper;

import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.model.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    private final ModelMapper modelMapper;

    public QuizMapper() {
        this.modelMapper = new ModelMapper();
    }

    public QuizDto mapQuizToQuizDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    public Quiz mapQuizDtoToQuiz(QuizDto quizDto) {
        return modelMapper.map(quizDto, Quiz.class);
    }

}
