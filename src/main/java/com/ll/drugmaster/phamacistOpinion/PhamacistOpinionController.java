package com.ll.drugmaster.phamacistOpinion;

import com.ll.drugmaster.counsel.Counsel;
import com.ll.drugmaster.counsel.CounselService;
import com.ll.drugmaster.member.Member;
import com.ll.drugmaster.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PhamacistOpinionController {

    private final PhamacistOpinionService phamacistOpinionService;
    private final CounselService counselService;
    private final MemberService memberService;

    //   todo: @GetMapping("/phamacistOpinion/{memberId}")
    @GetMapping("/phamacistOpinion/list")
    public String list(Model model) {
        List<PhamacistOpinion> phamacistOpinionList = this.phamacistOpinionService.getList();
        model.addAttribute("phamacistOpinionList", phamacistOpinionList);
        return "phamacist_opinion_list";
    }

    @GetMapping("/phamacistOpinion/detail/{phamacistOpinion_id}")
    public String detail(Model model, @PathVariable("phamacistOpinion_id") Long poid) {
        PhamacistOpinion phamacistOpinion = this.phamacistOpinionService.getPharmacistOpinion(poid);
        model.addAttribute("phamacistOpinion", phamacistOpinion);
        return "phamacistOpinion_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/answer/create/{id}")
    public String createOpinion(Model model, @PathVariable("id") Long id,
                                @Valid PhamacistOpinionForm phamacistOpinionForm,
                                BindingResult bindingResult, Principal principal) {

        Counsel counsel = this.counselService.getCounsel(id);
        Member member = this.memberService.getUser(principal.getName());
        if(bindingResult.hasErrors()) {
            model.addAttribute("counsel", counsel);
            return "counsel_detail";
        }
        this.phamacistOpinionService.create(counsel, phamacistOpinionForm.getContent(), member);
        return String.format("redirect:/counsel/detail/%s", id);
    }

}
