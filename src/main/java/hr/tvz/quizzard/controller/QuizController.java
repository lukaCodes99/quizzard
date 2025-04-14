package hr.tvz.quizzard.controller;

import hr.tvz.quizzard.model.Role;
import hr.tvz.quizzard.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final List<UserEntity> userEntityList = Arrays.asList(
            new UserEntity(1, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(2, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(3, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(4, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(5, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(6, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(7, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(8, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(9, "lzka", "sdad", "fdasda", Role.USER),
            new UserEntity(10, "lzka", "sdad", "fdasda", Role.USER)
    );

    @GetMapping()
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userEntityList);
    }


}
