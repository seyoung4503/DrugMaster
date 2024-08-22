package com.ll.drugmaster.MemberDrug;

import com.ll.drugmaster.drug.Drug;
import com.ll.drugmaster.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class MemberDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberDrugId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    // 복용 시작 날짜, 날짜별로 약물 복용 조회 (데이터베이스에 계속 쌓이기 때문)
    // 약사들이 startDate로 조회 후 opinion 작성
    private LocalDate startDate;

    // todo : member-drug 테이블에 insert 방법?


}
