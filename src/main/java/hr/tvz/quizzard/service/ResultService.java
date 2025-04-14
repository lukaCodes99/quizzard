package hr.tvz.quizzard.service;

import hr.tvz.quizzard.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

}
