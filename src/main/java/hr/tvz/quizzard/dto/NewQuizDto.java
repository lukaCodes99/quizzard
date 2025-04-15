package hr.tvz.quizzard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NewQuizDto {

    private String title;
    private String description;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate creationDate;
    private String category;
    private List<QuestionDto> questions;
}
