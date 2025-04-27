package hr.tvz.quizzard.service;

import hr.tvz.quizzard.dto.ResultDto;
import hr.tvz.quizzard.model.Result;
import hr.tvz.quizzard.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public Page<ResultDto> getMyResults(Integer userId, Pageable pageableObject) {
        return resultRepository.findAllByUserId_Id(userId, pageableObject)
                .map(result -> new ResultDto(
                        result.getId(),
                        result.getScore(),
                        result.getDate(),
                        result.getQuizId().getId(),
                        result.getQuizId().getId()
                ));
    }
}
