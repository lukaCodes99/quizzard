package hr.tvz.quizzard.model;

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
    private Integer resultID;

    private Double score;
    private Integer correctAnswers;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity userId;
}
