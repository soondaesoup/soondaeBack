package com.soondae.camp.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_memberRefreshToekn")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRefreshToken {

    @Id
    private String memail;

    private String refreshStr;

    private long expiredDate;

}
