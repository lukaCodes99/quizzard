package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.helpers.PageableHelper;
import hr.tvz.quizzard.model.FlaggedContent;
import hr.tvz.quizzard.service.FlaggedContentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/flagged-content")
@PreAuthorize("hasAnyAuthority('admin', 'moderator')")
public class FlaggedContentController {

    private final FlaggedContentService flaggedContentService;

    public FlaggedContentController(FlaggedContentService flaggedContentService) {
        this.flaggedContentService = flaggedContentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllFlaggedContent(
            @RequestParam(required = false) String entity,
            @RequestParam(required = false) Boolean solved,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") Boolean desc
    ) {
        try {
            Page<FlaggedContent> flaggedContent = flaggedContentService.findAllPagedAndSorted(
                    entity, solved,
                    PageableHelper.getPageableObject(pageIndex, pageSize, sortField, desc)
            );

            Map<String, Object> response = new LinkedHashMap<>();

            response.put("data", flaggedContent.getContent());
            response.put("pageIndex", flaggedContent.getNumber());
            response.put("rowCount", flaggedContent.getTotalElements());
            response.put("pageSize", flaggedContent.getSize());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<FlaggedContent> solveFlaggedContent(@PathVariable Integer id) {
        try {
            FlaggedContent updatedFlaggedContent = flaggedContentService.solveFlaggedContent(id);
            return new ResponseEntity<>(updatedFlaggedContent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<FlaggedContent> addFlaggedContent(@RequestBody FlaggedContent flaggedContent) {
        try {
            FlaggedContent savedFlaggedContent = flaggedContentService.saveFlaggedContent(flaggedContent);
            return new ResponseEntity<>(savedFlaggedContent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
