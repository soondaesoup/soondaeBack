package com.soondae.camp.member.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDetailWithMemberDTO {

    private Long mno;
    private String maddress;
    private String mnickName;
    private String memail;
    private String mphone;

}
