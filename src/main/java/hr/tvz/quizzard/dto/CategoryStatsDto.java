package hr.tvz.quizzard.dto;

import hr.tvz.quizzard.model.QuizCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryStatsDto {
    private QuizCategory category;
    private Long totalAttempts;
    private Double averageScore;
}
