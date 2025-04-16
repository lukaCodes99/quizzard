package hr.tvz.quizzard.service;

import hr.tvz.quizzard.model.FlaggedContent;
import hr.tvz.quizzard.repository.FlaggedContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlaggedContentService {

    private final FlaggedContentRepository flaggedContentRepository;


    public Page<FlaggedContent> findAllPagedAndSorted(String entity, Pageable pageableObject) {
        return flaggedContentRepository.findAllByEntity(entity, pageableObject);
    }
}
