package com.soondae.camp.board.dto;

import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.member.entity.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardInsertDTO {

    private String btitle;

    private boolean bstate = false;

    private String bcategory;

    private String bcontent;

    private String bprice;

    private boolean bdeleted = false;

    private Long mno;

    private List<BoardImageDTO> imageDTOS = new ArrayList<>();
}
