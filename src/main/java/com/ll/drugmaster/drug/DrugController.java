package com.ll.drugmaster.drug;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DrugController {

    private final DrugRepository drugRepository;
    private final DrugService drugService;
    @GetMapping("/drug/info")
    public String DrugInfo(Model model) {
        List<Drug> drugList = this.drugService.getList();
        model.addAttribute("drugList", drugList);
        return "Drug_info";
    }
}
