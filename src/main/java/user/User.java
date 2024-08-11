package user;

import phamacistOpinion.PhamacistOpinion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private Integer userAge;

    @Email(message = "이메일을 확인해주세요")
    private String userEmail;

    // 의사 소견
    @OneToOne(mappedBy = "User")
    private PhamacistOpinion phamacistOpinion;


}
