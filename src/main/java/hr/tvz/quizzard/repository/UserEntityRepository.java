package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}
