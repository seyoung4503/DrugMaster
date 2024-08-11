package com.ll.drugmaster.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/debug/member/list")
    public String list(Model model) {
        List<Member> memberList = this.memberRepository.findAll();
        model.addAttribute(memberList);
        return "member_list";
    }
}
