package com.soondae.camp.reply.service;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.member.entity.Member;
import com.soondae.camp.reply.dto.ReplyInsertDTO;
import com.soondae.camp.reply.dto.ReplyUpdateReqDTO;
import com.soondae.camp.reply.entity.Reply;

public interface ReplyService {

    Long replyRegister(ReplyInsertDTO replyInsertDTO);

    void replyRemove(Long rno);

    Long replyUpdate(ReplyUpdateReqDTO replyUpdateReqDTO);

    default Reply replyInsertDTOtoEntity(ReplyInsertDTO replyInsertDTO) {

        Board board = Board.builder()
                .bno(replyInsertDTO.getBno())
                .build();

        Member member = Member.builder()
                .mno(replyInsertDTO.getMno())
                .build();

        return Reply.builder()
                .rtext(replyInsertDTO.getRtext())
                .board(board)
                .member(member)
                .build();
    }

}
