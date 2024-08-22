package com.ll.drugmaster.phamacist;

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

    private String password;

//    // 약사 의견 삭제에도 모든 의견이 삭제됨
//    @OneToMany(mappedBy = "phamacist", cascade = CascadeType.REMOVE)
//    private List<PhamacistOpinion> phamacistOpinionList;

    // todo : 약사들 MemberDrug 테이블 조회, opinion 작성 기능

}
