package hr.tvz.quizzard.mapper;

import hr.tvz.quizzard.dto.ResultDto;
import hr.tvz.quizzard.model.Result;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {

    private final ModelMapper modelMapper;

    public ResultMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ResultDto mapResultToResultDto(Result result) {
        return modelMapper.map(result, ResultDto.class);
    }

    public Result mapResultDtoToResult(ResultDto resultDto) {
        return modelMapper.map(resultDto, Result.class);
    }

}
