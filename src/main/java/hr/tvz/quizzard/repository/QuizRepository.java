package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
