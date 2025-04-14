package hr.tvz.quizzard.repository;

import hr.tvz.quizzard.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
