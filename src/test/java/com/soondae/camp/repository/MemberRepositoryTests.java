package com.soondae.camp.repository;

import com.soondae.camp.member.entity.Member;
import com.soondae.camp.member.entity.MemberRole;
import com.soondae.camp.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 101).forEach(value -> {
            Member member = Member.builder()
                    .memail("kiteho"+value+"@gmail.com")
                    .mpw(passwordEncoder.encode("1111"))
                    .mnickName("서연호"+value)
                    .maddress("종로"+value)
                    .mphone("010-1234-6789-"+value)
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(value > 50) {
                member.addMemberRole(MemberRole.MEMBER);
            }
            if(value > 80) {
                member.addMemberRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testReadOne() {
        String memail = "kiteho10@gmail.com";
        Optional<Member> result = memberRepository.findByEmail(memail);
        result.ifPresent(member -> {
            log.info(member);
        });
    }

}
