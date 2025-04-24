package hr.tvz.quizzard.jwt;


import hr.tvz.quizzard.model.RefreshToken;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    UserEntityRepository userEntityRepository;

    public RefreshToken generateRefreshToken(String username) {
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        RefreshToken refreshToken = RefreshToken.builder()
                .userEntityId(userEntity)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(43200000))//12 h
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token has expired. Login again.");
        }
        return token;
    }

    @Transactional
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    @Transactional
    public void deleteRefreshTokenByUsername(UserEntity userEntity) {
        refreshTokenRepository.deleteByUserEntityId(userEntity);
    }
}
