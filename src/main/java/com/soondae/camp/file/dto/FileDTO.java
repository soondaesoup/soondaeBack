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
public class FileDTO {

    private String fuuid;


    private String fname;


    private Board board;
}
