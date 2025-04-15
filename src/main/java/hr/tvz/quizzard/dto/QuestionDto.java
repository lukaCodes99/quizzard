package hr.tvz.quizzard.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDto {

    private Integer id;
    private String text;
    private String type;

    private List<AnswerDto> answers = new ArrayList<>();
}
