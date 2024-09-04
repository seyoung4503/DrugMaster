package com.ll.drugmaster.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;


//    @Override
//    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
//        Optional<Member> optionalMember = memberRepository.findByMemberName(memberName);
//        if (optionalMember.isEmpty()) {
//            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
//        }
//        Member member = optionalMember.get();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(memberName)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
//        }
//        return new User(member.getMemberName(), member.getPassword(), authorities);
//    }
    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        System.out.println("사용자 " + memberName);
        System.out.println(memberName.length());
        Optional<Member> _siteUser = this.memberRepository.findByMemberName(memberName);
        if (_siteUser.isEmpty()) {
            System.out.println("사용자 로드안됨: ");
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member siteUser = _siteUser.get();
        System.out.println("사용자 로드됨: " + siteUser.getMemberName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(memberName)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getValue()));
        }
        return new User(siteUser.getMemberName(), siteUser.getPassword(),
                authorities);
    }

//    @Override
//    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
//        Optional<Member> _siteUser = memberRepository.findByMemberName(memberName);
//        if (_siteUser.isEmpty()) {
//            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
//        }
//
//        Member siteUser = _siteUser.get();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(siteUser.getRole().getValue()));
//
//        return new User(siteUser.getMemberName(), siteUser.getPassword(), authorities);
//    }
}
