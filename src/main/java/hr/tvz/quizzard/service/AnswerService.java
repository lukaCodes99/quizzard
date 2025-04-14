package hr.tvz.quizzard.service;

import hr.tvz.quizzard.repository.AnswerRepository;
import hr.tvz.quizzard.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {

    private final UserEntityRepository userEntityRepository;
    private final AnswerRepository answerRepository;

}
