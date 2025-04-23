package hr.tvz.quizzard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FlaggedContent")
public class FlaggedContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;
    private Integer flagQuizId;
    private Integer flagQuestionId;
    private Integer flagAnswerId;
    private Integer flagUserId;

    private Boolean solved = false;

}
