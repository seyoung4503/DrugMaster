package com.ll.drugmaster;

import com.ll.drugmaster.member.Member;
import com.ll.drugmaster.member.MemberRepository;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinion;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DrugmasterApplicationTests {
	@Autowired
	private PhamacistOpinionRepository phamacistOpinionRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		// member 생성
		Member member = new Member();
		member.setMemberName("홍길동");
		member.setMemberAge(60);
		member.setMemberEmail("hgd@g.com");
		member.setPassword("1122");
		this.memberRepository.save(member);

		PhamacistOpinion po1 = new PhamacistOpinion();
		po1.setSubject("홍길동씨 상담일지");
		po1.setContent("a 정 5알, b 정 10알 처방");
		po1.setCreateDate(LocalDateTime.now());
		po1.setMember(member);
		this.phamacistOpinionRepository.save(po1);
	}

}
