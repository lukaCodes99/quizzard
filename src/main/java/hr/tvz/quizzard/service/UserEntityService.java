package hr.tvz.quizzard.service;

import hr.tvz.quizzard.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

}
