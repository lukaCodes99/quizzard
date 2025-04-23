package hr.tvz.quizzard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role;
}
