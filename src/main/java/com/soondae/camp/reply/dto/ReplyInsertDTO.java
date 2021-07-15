package com.soondae.camp.reply.dto;

import com.soondae.camp.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyInsertDTO {

    private String rtext;

    private Long bno;

    private Long mno;

}
