package com.ll.drugmaster.phamacistOpinion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhamacistOpinionService {

    public final PhamacistOpinionRepository phamacistOpinionRepository;

    public List<PhamacistOpinion> getList() {
        return this.phamacistOpinionRepository.findAll();

    }
}
