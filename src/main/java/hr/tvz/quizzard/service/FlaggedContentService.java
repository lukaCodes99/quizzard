package hr.tvz.quizzard.service;

import hr.tvz.quizzard.model.FlaggedContent;
import hr.tvz.quizzard.model.UserEntity;
import hr.tvz.quizzard.repository.FlaggedContentRepository;
import hr.tvz.quizzard.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class FlaggedContentService {

    private final FlaggedContentRepository flaggedContentRepository;
    private final UserEntityRepository userEntityRepository;

    public Page<FlaggedContent> findAllPagedAndSorted(String entity, Boolean solved, Pageable pageableObject) {
        return flaggedContentRepository.findAllByEntity(entity, solved, pageableObject);
    }

    public FlaggedContent saveFlaggedContent(FlaggedContent flaggedContent) {
        return flaggedContentRepository.save(flaggedContent);
    }

    public FlaggedContent solveFlaggedContent(Integer id) {
        FlaggedContent flaggedContent = flaggedContentRepository.findById(id).orElse(null);
        if (flaggedContent != null) {
            flaggedContent.setSolved(true);
            long userFlaggedCount = flaggedContentRepository.countByFlagUserId(flaggedContent.getFlagUserId());
            if(userFlaggedCount > 4){
                UserEntity flaggedUser = userEntityRepository.findById(flaggedContent.getFlagUserId()).orElse(null);
                if(flaggedUser != null){
                    flaggedUser.setEnabled(false);
                    userEntityRepository.save(flaggedUser);
                }
            }
            return flaggedContentRepository.save(flaggedContent);
        }
        return null;
    }
}
