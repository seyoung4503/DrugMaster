package com.ll.drugmaster.member;

import com.ll.drugmaster.DataNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Member> getList() {
        return this.memberRepository.findAll();
    }

//    public Member getMember() {
//
//    }

    public Member create(String memberName, String memberEmail, String password, Integer memberAge, Boolean isPhamacist) {
        Member member = new Member();
        member.setMemberName(memberName);
        member.setMemberAge(memberAge);
        member.setMemberEmail(memberEmail);
        member.setRole(MemberRole.MEMBER);
        member.setIsPhamacist(isPhamacist);
        member.setPassword(passwordEncoder.encode(password));
        this.memberRepository.save(member);
        return member;
    }

    public Member findMemberByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }



    public Member getUser(String memberName) {
        Optional<Member> member = this.memberRepository.findByMemberName(memberName);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("user not found");
        }
    }
}
