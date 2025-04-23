package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {


    @Query(value = "SELECT q " +
            "FROM Quiz q " +
            "WHERE (:category IS NULL OR q.category = :category) " +
            "AND (:averageRatingFrom IS NULL OR q.averageRating >= :averageRatingFrom) " +
            "AND (:averageRatingTo IS NULL OR q.averageRating <= :averageRatingTo) " +
            "AND (:creationDateFrom IS NULL OR q.creationDate >= :creationDateFrom) " +
            "AND (:creationDateTo IS NULL OR q.creationDate <= :creationDateTo) " +
           // "AND (:description IS NULL OR LOWER(q.description) LIKE LOWER(CONCAT('%', :description, '%'))) ") //ovo ne radi jer hibernate želi sve provjeriti u OR iako je prvi uvjet true.....:(
            //"AND (:title IS NULL OR LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%'))) ")
            "AND (LOWER(q.title) LIKE LOWER(CONCAT('%',:title,'%')) OR :title IS NULL) " +
            "AND (LOWER(q.description) LIKE LOWER(CONCAT('%',:description,'%')) OR :description IS NULL)")
    Page<Quiz> findAllFiltered(@Param("category") String category,
                               @Param("averageRatingFrom") Double averageRatingFrom,
                               @Param("averageRatingTo") Double averageRatingTo,
                               @Param("creationDateFrom") String creationDateFrom,
                               @Param("creationDateTo") String creationDateTo,
                               @Param("description") String description,
                               @Param("title") String title,
                               Pageable pageable);

    List<Quiz> findByOwnerId_Id(Integer userId);

    List<Quiz> findTopByOrderByAverageRatingDesc();

    default List<Quiz> findTopByAverageRating(int limit) {
        return findTopByOrderByAverageRatingDesc().stream().limit(limit).toList();
    }
}
