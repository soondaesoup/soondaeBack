package com.soondae.camp.board.dto;

import com.soondae.camp.file.dto.BoardImageDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @CreatedDate
    private LocalDateTime bregDate;
    @LastModifiedDate
    private LocalDateTime bmodDate;

    @Builder.Default
    private List<BoardImageDTO> imageDTOS = new ArrayList<>();



}
