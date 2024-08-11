package com.ll.drugmaster.phamacistOpinion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.ll.drugmaster.member.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PhamacistOpinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PhamOpinionId;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToOne
    private Member member;
}
