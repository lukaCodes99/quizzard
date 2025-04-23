package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "answers")
@Getter
@Setter
@ToString(exclude = "questionId")
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    @JsonIgnore
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonBackReference
    private Question questionId;
}

