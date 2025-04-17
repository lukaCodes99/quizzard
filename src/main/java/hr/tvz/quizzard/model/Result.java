package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //count() iz baze možemo dobiti, ne treba atribut u klasi

    private Double score;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "quizId", referencedColumnName = "id")
    private Quiz quizId;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity userId;

    public Result(UserEntity userId, Quiz quizId, LocalDate date, Double score) {
        this.userId = userId;
        this.quizId = quizId;
        this.date = date;
        this.score = score;
    }
}
