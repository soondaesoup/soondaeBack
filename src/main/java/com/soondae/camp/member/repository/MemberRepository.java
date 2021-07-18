package com.soondae.camp.member.repository;

import com.soondae.camp.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = "memberRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.memail=:memail")
    Optional<Member> findByEmail(String memail);

//    Optional<Member> getByMember(Long mno);

}
