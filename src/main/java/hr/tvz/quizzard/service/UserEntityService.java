package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.RegistrationRequestDto;
import hr.tvz.quizzard.model.Role;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean existsByUsernameOrEmail(String username, String email) {
        return userEntityRepository.existsByUsernameOrEmail(username, email);
    }

    public UserEntity registerUser(RegistrationRequestDto registrationRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationRequestDto.getUsername());
        userEntity.setEmail(registrationRequestDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
        userEntity.setRole(Role.USER);
        return userEntityRepository.save(userEntity);
    }



    public boolean getEnabledUserByUsername(String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getEnabled();
    }

    public UserEntity getUserByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
