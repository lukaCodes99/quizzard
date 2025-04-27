package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.dto.AuthRequestDto;
import hr.tvz.quizzard.dto.JwtResponseDto;
import hr.tvz.quizzard.dto.RegistrationRequestDto;
import hr.tvz.quizzard.jwt.JwtService;
import hr.tvz.quizzard.jwt.RefreshTokenService;
import hr.tvz.quizzard.model.RefreshToken;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.service.UserEntityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;
    private UserEntityService userEntityService;


    @PostMapping("/login")
    public JwtResponseDto authenticate(@RequestBody AuthRequestDto request) {

        UserEntity userEntity = userEntityService.getUserByUsername(request.getUsername());
        if(userEntity == null || !userEntity.getEnabled()) {
            throw new AccessDeniedException("User is not active");
        } else refreshTokenService.deleteRefreshTokenByUsername(userEntity);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if(authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(request.getUsername());
            return JwtResponseDto.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
        else throw new UsernameNotFoundException("Invalid username or password");
    }

    @PostMapping("/refreshToken")
    @PreAuthorize("hasAnyAuthority('admin', 'moderator', 'user')")
    public JwtResponseDto refreshToken(@RequestParam String token) {
        return refreshTokenService.findByToken(token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserEntityId)
                .map(_ -> { //prije nije moglo unnamed
                    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String generatedToken = jwtService.generateToken(userDetails);
                    return JwtResponseDto.builder()
                            .accessToken(generatedToken)
                            .refreshToken(token)
                            .build();
                }).orElseThrow(() -> new EntityNotFoundException("Refresh token does not exist in the database!"));
    }

    @Transactional
    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<Map<String, String>> logoutUser(@RequestParam String refreshToken) {

        refreshTokenService.findByToken(refreshToken)
                .ifPresent(foundToken -> refreshTokenService.deleteRefreshToken(foundToken.getToken()));

        SecurityContextHolder.clearContext();
        Map<String, String> response = new HashMap<>();
        response.put("message", "User logged out successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {

        if(userEntityService.existsByUsernameOrEmail(registrationRequestDto.getUsername(), registrationRequestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exist");
        }
        UserEntity userEntity = userEntityService.registerUser(registrationRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @PostMapping("/{userEntityId}/change-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeUserRole(@PathVariable Integer userEntityId, @RequestParam String newRole) {
        try{
            UserEntity userEntity = userEntityService.changeUserRole(userEntityId, newRole);
            return ResponseEntity.ok(userEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
