package com.soondae.camp.reply.dto;

import com.soondae.camp.board.entity.Board;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDTO {

    private Long rno;
    private Long bno;

    private String rtext;

    private String rwriter;

    private LocalDateTime rregDate;

    private LocalDateTime rmodDate;

    private boolean rdeleted;

}
