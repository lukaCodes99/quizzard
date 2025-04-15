package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private QuizCategory category;

    private Double averageRating;
    private Integer ratingCount;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private UserEntity ownerId;

    @OneToMany(mappedBy = "quizId", cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();
}

