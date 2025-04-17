package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Collection<Object> findByQuizId_Id(Integer quizId);
}
