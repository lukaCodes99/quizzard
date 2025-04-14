package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double score;

    @JsonFormat(pattern = "dd--MM-yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "quizId", referencedColumnName = "id")
    private Quiz quizId;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity userId;
}
