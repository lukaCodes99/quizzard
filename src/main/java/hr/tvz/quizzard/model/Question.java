package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString(exclude = {"answers", "quizId"})
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "quizId")
    @JsonBackReference
    private Quiz quizId;

    @OneToMany(mappedBy = "questionId", cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();
}

