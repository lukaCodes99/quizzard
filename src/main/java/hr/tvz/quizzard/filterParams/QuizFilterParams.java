package hr.tvz.quizzard.filterParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizFilterParams {
    private String title;
    private String description;
    private String creationDateFrom;
    private String creationDateTo;
    private String category;
    private Double averageRatingFrom;
    private Double averageRatingTo;
}
