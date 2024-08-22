package com.ll.drugmaster.drug;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DrugService {
    private final DrugRepository drugRepository;

    public List<Drug> getList() {
        return this.drugRepository.findAll();
    }
}
