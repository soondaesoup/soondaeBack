package com.soondae.camp.member.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long mno;

    private String mnickName;

    private String maddress;


}
