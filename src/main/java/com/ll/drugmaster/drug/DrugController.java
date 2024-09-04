package com.ll.drugmaster.drug;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class DrugController {

    private final DrugService drugService;
    @GetMapping("/drug/info")
    public String DrugInfo(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Drug> paging = this.drugService.getList(page);
        model.addAttribute("paging", paging);
        return "Drug_info";
    }

    @GetMapping(value = "/drug/detail/{drugId}")
    public String detailDrug(Model model, @PathVariable("drugId") Long drugId) {
        Drug drug = this.drugService.getDrug(drugId);
        model.addAttribute("drug", drug);

        return "drug_detail";
    }

    @GetMapping("/drug/create")
    public String drugCreate(DrugForm drugForm) {
        return "drugCreate_form";
    }

    @PostMapping("/drug/create")
    public String drugCreate(@Valid DrugForm drugForm, BindingResult bindingResult) {
        // todo : create 저장
        if (bindingResult.hasErrors()) {
            return "drugCreate_form";
        }
        this.drugService.create(drugForm.getDrugName(),drugForm.getSubject(), drugForm.getContent());
        return "redirect:/drug/info";
    }
}

