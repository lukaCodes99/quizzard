package hr.tvz.quizzard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private Integer id;
    private String title;
    private String description;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate creationDate;
    private String category;
    private Double averageRating;
    private Integer ratingCount;
    private Integer ownerId;

}
