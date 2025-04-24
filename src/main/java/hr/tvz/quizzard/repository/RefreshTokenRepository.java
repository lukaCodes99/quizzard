package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.RefreshToken;
import hr.tvz.quizzard.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken r WHERE r.expiryDate <= CURRENT_TIMESTAMP")
    void deleteAllExpiredTokens();

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken r WHERE r.userEntityId.id = :userId")
    void deleteByUserId(@Param("userId") Integer userEntityId);

    @Modifying
    @Transactional
    void deleteByToken(String token);

    @Modifying
    @Transactional
    void deleteByUserEntityId(UserEntity userEntity);
}
