package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.*;
import hr.tvz.quizzard.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/platform")
    public PlatformStatisticsDto getPlatformStatistics() {
        return statisticsService.getPlatformStatistics();
    }

    @GetMapping("/top-quizzes/attempts")
    public List<QuizStatisticsDto> getTopQuizzesByAttempts(@RequestParam(defaultValue = "5") int limit) {
        return statisticsService.getTopQuizzesByAttempts(limit);
    }

    @GetMapping("/top-quizzes/ratings")
    public List<QuizStatisticsDto> getTopQuizzesByRating(@RequestParam(defaultValue = "5") int limit) {
        return statisticsService.getTopQuizzesByRating(limit);
    }

    @GetMapping("/categories")
    public List<CategoryStatsDto> getCategoryStats() {
        return statisticsService.getStatsPerCategory();
    }

    @GetMapping("/attempts/trend")
    public Map<LocalDate, Long> getAttemptTrends(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return statisticsService.getAttemptTrends(from, to);
    }

    @GetMapping("/score-distribution/{quizId}")
    public Map<String, Long> getScoreDistribution(@PathVariable Integer quizId) {
        return statisticsService.getScoreDistribution(quizId);
    }
}
