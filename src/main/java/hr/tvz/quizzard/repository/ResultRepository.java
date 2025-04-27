package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.dto.QuizStatisticsDto;
import hr.tvz.quizzard.model.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {

    long countByQuizId_Id(Integer quizId);

    @Query("SELECT AVG(r.score) FROM Result r WHERE r.quizId.id = :quizId")
    Double findAverageScoreForQuiz(Integer quizId);

    List<Result> findByDateBetween(LocalDate from, LocalDate to);

    List<Result> findByQuizId_Id(Integer quizId);

    @Query("SELECT new hr.tvz.quizzard.dto.QuizStatisticsDto(r.quizId.id, r.quizId.title, AVG(r.score), COUNT(r), r.quizId.averageRating) " +
            "FROM Result r GROUP BY r.quizId.id, r.quizId.title, r.quizId.averageRating ORDER BY COUNT(r) DESC")
    List<QuizStatisticsDto> findTopQuizzesByAttempts(org.springframework.data.domain.Pageable pageable);

    default List<QuizStatisticsDto> findTopQuizzesByAttempts(int limit) {
        return findTopQuizzesByAttempts(org.springframework.data.domain.PageRequest.of(0, limit));
    }


    Page<Result> findAllByUserId_Id(Integer userId, Pageable pageableObject);
}

