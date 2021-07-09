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

    private String rtext;

    private String rwriter;

    @CreatedDate
    private LocalDateTime rregDate;

    @LastModifiedDate
    private LocalDateTime rmodDate;

    @Builder.Default
    private boolean rdeleted = false;

    private Board board;

    public void changeReply(String rtext){
        this.rtext = rtext;
    }

    public void deleteReply(boolean rdeleted) {
        this.rdeleted = rdeleted;
    }

}
