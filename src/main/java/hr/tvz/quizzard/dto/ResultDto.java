package hr.tvz.quizzard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private Integer id;
    private Double score;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    private Integer quizId;
    private Integer userId;

}
