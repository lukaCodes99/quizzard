package hr.tvz.quizzard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlatformStatisticsDto {
    private Long totalUsers;
    private Long totalQuizzes;
    private Long totalQuestions;
    private Long totalAnswers;
    private Long totalAttempts;
}
