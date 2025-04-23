package hr.tvz.quizzard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizStatisticsDto {
    private Integer quizId;
    private String title;
    private Double averageScore;
    private Long attemptCount;
    private Double averageRating;
}
