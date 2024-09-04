package com.ll.drugmaster.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/debug/member/list")
    public String list(Model model) {
        List<Member> memberList = this.memberService.getList();
        model.addAttribute("memberList", memberList);
        return "member_list";
    }

    // todo : 마이 페이지 기능 완성하기
//    @GetMapping("/mypage")
//    public String mypage(Model model) {
//        return "mypage";
//    }
    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Handle case where user is not authenticated
            return "redirect:/login";
        }

        String username = userDetails.getUsername();

        Member member = memberService.findMemberByMemberName(username);

        if (member != null) {
            model.addAttribute("member", member);
        }

        return "mypage";
    }

    @GetMapping("/user/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "signup_form";
    }

    @PostMapping("/user/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try {
            memberService.create(memberCreateForm.getMemberName(),
                    memberCreateForm.getMemberEmail(), memberCreateForm.getPassword1(), memberCreateForm.getMemberAge(),
                    memberCreateForm.getIsPhamacist());
        }catch(
            DataIntegrityViolationException e) {
                e.printStackTrace();
                bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
                return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
    @GetMapping("/user/login")
    public String login() {
        return "login_form";
    }

//    @GetMapping("/opinion/list")
//    public String OpinionList(Model model) {
//        List<Member> List = this.memberService.
//        return "question_list";
//    }
}
