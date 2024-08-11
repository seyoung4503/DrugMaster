package com.ll.drugmaster.member;

import com.ll.drugmaster.phamacistOpinion.PhamacistOpinion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    private Integer memberAge;

    @Email(message = "이메일을 확인해주세요")
    private String memberEmail;

    private String password;

    // 의사 소견
    @OneToOne(mappedBy = "member")
    private PhamacistOpinion phamacistOpinion;


}
