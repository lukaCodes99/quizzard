package hr.tvz.quizzard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDate creationDate;
    private String category;
    private Double averageRating;
    private Integer ratingCount;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private UserEntity ownerId;

    @OneToMany(mappedBy = "quizId", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
}

