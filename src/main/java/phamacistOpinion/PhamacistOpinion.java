package phamacistOpinion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PhamacistOpinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PhamOpinion_id;



    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // @Todo : userId Relation 넣기
    @OneToOne
    private User user;
}
