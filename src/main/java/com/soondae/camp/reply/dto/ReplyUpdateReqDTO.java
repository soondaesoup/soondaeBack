package com.soondae.camp.reply.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyUpdateReqDTO {

    private String rtext;

    private Long rno;

}

