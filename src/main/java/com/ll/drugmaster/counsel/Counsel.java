package com.ll.drugmaster.counsel;

import com.ll.drugmaster.member.Member;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Counsel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "counsel", cascade = CascadeType.REMOVE)
    private List<PhamacistOpinion> pharmacistOpinionList;

    @ManyToOne
    private Member member;

    private String imageUrl;

}
