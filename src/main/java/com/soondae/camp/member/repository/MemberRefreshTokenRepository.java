package com.soondae.camp.member.repository;

import com.soondae.camp.member.entity.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, String> {

    Optional<MemberRefreshToken> findByRefreshStr(String refreshStr);

}
