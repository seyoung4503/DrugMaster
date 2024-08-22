package com.ll.drugmaster;

import com.ll.drugmaster.MemberDrug.MemberDrug;
import com.ll.drugmaster.MemberDrug.MemberDrugRepository;
import com.ll.drugmaster.drug.Drug;
import com.ll.drugmaster.drug.DrugRepository;
import com.ll.drugmaster.member.Member;
import com.ll.drugmaster.member.MemberRepository;
import com.ll.drugmaster.phamacist.Phamacist;
import com.ll.drugmaster.phamacist.PhamacistRepository;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinion;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DrugmasterApplicationTests {
	@Autowired
	private PhamacistOpinionRepository phamacistOpinionRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PhamacistRepository phamacistRepository;

	@Autowired
	private DrugRepository drugRepository;

	@Autowired
	private MemberDrugRepository memberDrugRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testMember() {
		Member member = new Member();
		member.setMemberName("김림미");
		member.setMemberAge(60);
		member.setMemberEmail("klm@gmail.com");
		member.setPassword("1234");
		this.memberRepository.save(member);

		Member member2 = new Member();
		member2.setMemberName("최민기");
		member2.setMemberAge(45);
		member2.setMemberEmail("cmg@gmail.com");
		member2.setPassword("5678");
		this.memberRepository.save(member2);
	}

	@Test
	void testPO() {
		Optional<Member> om = this.memberRepository.findById(1L);
		assertTrue(om.isPresent());

		Member m1 = om.get();

		PhamacistOpinion op = new PhamacistOpinion();
		op.setMember(m1);
		op.setContent(m1.getMemberName() + " 상담일지");
		op.setSubject("a 5정, b 10정");
		op.setCreateDate(LocalDateTime.now());
		this.phamacistOpinionRepository.save(op);
	}

	@Test
	void testPO2() {
		Optional<Member> om = this.memberRepository.findById(1L);
		assertTrue(om.isPresent());

		Member m1 = om.get();

		PhamacistOpinion op = new PhamacistOpinion();
		op.setMember(m1);
		op.setContent("a 5정, b 11정");
		op.setSubject(m1.getMemberName() + " 상담일지");
		op.setCreateDate(LocalDateTime.now());
		this.phamacistOpinionRepository.save(op);
	}

	// drug 테이블에 추가
	@Test
	void testAddDrug() {
		Drug d1 = new Drug();
		d1.setDrugName("a");
		this.drugRepository.save(d1);

		Drug d2 = new Drug();
		d2.setDrugName("b");
		this.drugRepository.save(d2);
	}

	// 멤버의 drug 테이블 넣기
	@Test
	void testInsertMember_DrugInfo() {
		Optional<Member> om = this.memberRepository.findById(2L);
		assertTrue(om.isPresent());

		Member m1 = om.get();

		Optional<Drug> od = this.drugRepository.findById(1L);
		assertTrue(od.isPresent());

		Drug d1 = od.get();

		MemberDrug memberDrug = new MemberDrug();
		memberDrug.setDrug(d1);
		memberDrug.setMember(m1);
		memberDrug.setStartDate(LocalDate.now());

		this.memberDrugRepository.save(memberDrug);
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

		Member member2 = new Member();
		member2.setMemberName("김철수");
		member2.setMemberAge(50);
		member2.setMemberEmail("kcs");
		member2.setPassword("1234");
		this.memberRepository.save(member2);

		Phamacist p1 = new Phamacist();
		p1.setPhamName("의존김");
		p1.setPhamEmail("ojk");
		p1.setPassword("1255");
		this.phamacistRepository.save(p1);

		PhamacistOpinion po1 = new PhamacistOpinion();
		po1.setSubject("홍길동씨 상담일지");
		po1.setContent("a 정 5알, b 정 10알 처방");
		po1.setCreateDate(LocalDateTime.now());
		po1.setMember(member);
//		po1.setPhamacist(p1);
		this.phamacistOpinionRepository.save(po1);

		PhamacistOpinion po2 = new PhamacistOpinion();
		po2.setSubject("김철수씨 상담일지");
		po2.setContent("a 정 3알, b 정 3알 처방");
		po2.setCreateDate(LocalDateTime.now());
		po2.setMember(member2);
//		po2.setPhamacist(p1);
		this.phamacistOpinionRepository.save(po2);


	}

}
