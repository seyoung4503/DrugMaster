package com.ll.drugmaster.phamacistOpinion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PhamacistOpinionController {

    private final PhamacistOpinionService phamacistOpinionService;
    //   todo: @GetMapping("/phamacistOpinion/{memberId}")
    @GetMapping("/phamacistOpinion/list")
    public String list(Model model) {
        List<PhamacistOpinion> phamacistOpinionList = this.phamacistOpinionService.getList();
        model.addAttribute("phamacistOpinionList", phamacistOpinionList);
        return "phamacist_opinion_list";
    }

}
