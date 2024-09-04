package com.ll.drugmaster.drug;


import com.ll.drugmaster.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DrugService {
    private final DrugRepository drugRepository;

    public Page<Drug> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.drugRepository.findAll(pageable);
    }

    public Drug getDrug(Long id) {
        Optional<Drug> drug = this.drugRepository.findById(id);
        if (drug.isPresent()) {
            return drug.get();
        } else {
            throw new DataNotFoundException("drug not found");
        }

    }

    public void create(String Drugname, String subject, String content) {
        Drug drug = new Drug();
        drug.setDrugName(Drugname);
        drug.setContent(content);
        drug.setSubject(subject);
        this.drugRepository.save(drug);
    }
}
