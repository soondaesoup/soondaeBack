package com.soondae.camp.reply.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.entity.Reply;

public interface ReplyService {



    default ReplyDTO entityToDTO(Reply entity){
        return ReplyDTO.builder()
                .rno(entity.getRno())
                .rtext(entity.getRtext())
                .rwriter(entity.getRwriter())
                .rdeleted(entity.isRdeleted())
                .rregDate(entity.getRregDate())
                .rmodDate(entity.getRmodDate())
                .build();
    }

    ReplyDTO modify(ReplyDTO replyDTO);



}
