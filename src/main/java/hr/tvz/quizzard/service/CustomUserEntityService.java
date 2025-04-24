package hr.tvz.quizzard.service;

import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserEntityService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    //obratiti paznju na ovaj konstruktor tj nepostojanje konstruktora,
    // koristimo autowired da bi se izbjegla kružna ovisnost između security config i ovoga

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapRoleToAuthority(userEntity)
        );
    }

    private Collection<GrantedAuthority> mapRoleToAuthority(UserEntity userEntity) {
        return List.of(() -> userEntity.getRole().name());
    }
}
