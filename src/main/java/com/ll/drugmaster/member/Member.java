package com.ll.drugmaster.member;

import com.ll.drugmaster.MemberDrug.MemberDrug;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<PhamacistOpinion> phamacistOpinionList;

    // 복용 약 정보
    @OneToMany(mappedBy = "member")
    private Set<MemberDrug> memberDrugs = new HashSet<>();

}
