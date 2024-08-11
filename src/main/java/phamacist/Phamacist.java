package phamacist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Phamacist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phamId;

    private String phamName;

    @Email(message = "유효한 양식 필요")
    private String phamEmail;



}
