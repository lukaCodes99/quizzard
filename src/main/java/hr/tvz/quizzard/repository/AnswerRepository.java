package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.Answer;
import hr.tvz.quizzard.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
