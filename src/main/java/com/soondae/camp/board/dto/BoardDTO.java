package com.soondae.camp.board.dto;

import com.soondae.camp.file.dto.BoardImageDTO;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {

    private Long bno;

    private boolean bstate = false;

    private String bcategory;

    private String btitle;

    private String bcontent;

    private String bprice;

    private String bwriter;
    private LocalDateTime bregDate;
    private LocalDateTime bmodDate;

    @Builder.Default
    private List<BoardImageDTO> imageDTOS = new ArrayList<>();



}
