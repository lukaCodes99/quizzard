package hr.tvz.quizzard.dto;


import lombok.Data;

@Data
public class AnswerDto {
    private Integer id;
    private String text;
    private boolean isCorrect;
}
