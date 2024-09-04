package com.ll.drugmaster.counsel;

import com.ll.drugmaster.DataNotFoundException;
import com.ll.drugmaster.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CounselService {
    private final CounselRepository counselRepository;

    public List<Counsel> getList() {
        return this.counselRepository.findAll();
    }

    public Page<Counsel> getListPage(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.counselRepository.findAll(pageable);
    }

    public Page<Counsel> getListPageByMember(int page, String memberName) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return counselRepository.findByMemberMemberName(memberName, pageable);
    }


    public Counsel getCounsel(Long id) {
        Optional<Counsel> counsel = this.counselRepository.findById(id);
        if (counsel.isPresent()) {
            return counsel.get();
        } else {
            throw new DataNotFoundException("data not found");
        }
    }

    public void create(String subject, String content, Member member, String imageUrl) {
        Counsel counsel = new Counsel();
        counsel.setSubject(subject);
        counsel.setContent(content);
        counsel.setMember(member);
        counsel.setCreateDate(LocalDateTime.now());
        counsel.setImageUrl(imageUrl);
        this.counselRepository.save(counsel);
    }
}
