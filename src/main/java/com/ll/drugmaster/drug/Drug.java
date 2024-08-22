package com.ll.drugmaster.drug;

import com.ll.drugmaster.MemberDrug.MemberDrug;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugId;

    private String drugName;

    // 환자 별 복용약 정보 테이블
    @OneToMany(mappedBy = "drug")
    private Set<MemberDrug> memberDrugs = new HashSet<>();

    // todo : drug 추가

    // todo : yolo api와 벡엔드 연결
}
