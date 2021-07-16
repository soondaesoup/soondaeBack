package com.soondae.camp.member.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "memberRoleSet")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    @Column(nullable = false)
    private String memail;
    @Column(nullable = false)
    private String mpw;
    @Column(nullable = false)
    private String mnickName;
    @Column(nullable = false)
    private String maddress;
    @Column(nullable = false)
    private String mphone;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> memberRoleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole) {
        memberRoleSet.add(memberRole);
    }

}
