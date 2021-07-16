package com.soondae.camp.file.dto;

import com.soondae.camp.board.entity.Board;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardImageDTO {

    private String fuuid;
    private String fname;
    private boolean fmain;
    private Long bno;

}
