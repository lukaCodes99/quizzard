package hr.tvz.quizzard.mapper;

import hr.tvz.quizzard.dto.NewQuizDto;
import hr.tvz.quizzard.dto.QuizDto;
import hr.tvz.quizzard.model.Question;
import hr.tvz.quizzard.model.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizMapper {

    private final ModelMapper modelMapper;
    private final QuestionMapper questionMapper;

    public QuizMapper(QuestionMapper questionMapper) {
        this.modelMapper = new ModelMapper();
        this.questionMapper = questionMapper;
    }

    public QuizDto mapQuizToQuizDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    public Quiz mapQuizDtoToQuiz(QuizDto quizDto) {
        return modelMapper.map(quizDto, Quiz.class);
    }

    public Quiz mapNewQuizDtoToQuiz(NewQuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz.setQuestions(
                quizDto.getQuestions().stream()
                        .map(questionDto -> {
                            Question question = questionMapper.mapQuestionDtoToQuestion(questionDto);
                            question.setQuizId(quiz); // Set the parent Quiz directly
                            question.getAnswers().forEach(answer -> answer.setQuestionId(question)); // Set parent Question for each Answer
                            return question;
                        })
                        .toList()
        );
        return quiz;
    }
}
