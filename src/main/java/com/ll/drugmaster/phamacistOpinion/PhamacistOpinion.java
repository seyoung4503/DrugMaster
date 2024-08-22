package com.ll.drugmaster.phamacistOpinion;

import com.ll.drugmaster.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    private Member member;

//    @ManyToOne
//    private Phamacist phamacist;
}
