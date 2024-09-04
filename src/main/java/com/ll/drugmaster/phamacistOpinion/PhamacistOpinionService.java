package com.ll.drugmaster.phamacistOpinion;

import com.ll.drugmaster.DataNotFoundException;
import com.ll.drugmaster.counsel.Counsel;
import com.ll.drugmaster.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PhamacistOpinionService {

    public final PhamacistOpinionRepository phamacistOpinionRepository;

    public List<PhamacistOpinion> getList() {
        return this.phamacistOpinionRepository.findAll();

    }

    public PhamacistOpinion getPharmacistOpinion(Long id) {
        Optional<PhamacistOpinion> phamacistOpinion = this.phamacistOpinionRepository.findById(id);
        if (phamacistOpinion.isPresent()) {
            return phamacistOpinion.get();
        } else {
          throw new DataNotFoundException("phamacist Opinion not found");
        }
    }

    public void create(Counsel counsel, String content, Member member) {
        PhamacistOpinion phamacistOpinion = new PhamacistOpinion();
        phamacistOpinion.setContent(content);
        phamacistOpinion.setCreateDate(LocalDateTime.now());
        phamacistOpinion.setCounsel(counsel);
        phamacistOpinion.setMember(member);

        this.phamacistOpinionRepository.save(phamacistOpinion);

    }

}
