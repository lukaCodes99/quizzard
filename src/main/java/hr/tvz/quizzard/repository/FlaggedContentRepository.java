package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.FlaggedContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlaggedContentRepository extends JpaRepository<FlaggedContent, Integer> {

    @Query("SELECT fc FROM FlaggedContent fc " +
           "WHERE (:entity IS NULL OR " +
            " (:entity = 'quiz' AND fc.flagQuiz IS NOT NULL) OR " +
            " (:entity = 'question' AND fc.flagQuestion IS NOT NULL) OR " +
            " (:entity = 'answer' AND fc.flagAnswer IS NOT NULL) )"
    )
    Page<FlaggedContent> findAllByEntity(
            @Param("entity") String entity,
            Pageable pageableObject);
}
