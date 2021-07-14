package com.soondae.camp.member.service;

import com.soondae.camp.member.dto.MemberDTO;
import com.soondae.camp.member.entity.Member;
import com.soondae.camp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("===username:"+username+"===");
        Optional<Member> result = memberRepository.findByEmail(username);
        if(result.isPresent()) {
            Member member = result.get();
            UserDetails op = new MemberDTO(username, member.getMpw(), member.getMemberRoleSet()
            .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList()));
            return op;
        }
        return null;
    }


}
