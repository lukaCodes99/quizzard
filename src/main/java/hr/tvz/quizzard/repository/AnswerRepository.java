package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Question, Integer> {
}
