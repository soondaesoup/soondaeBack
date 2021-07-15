package com.soondae.camp.reply.service;

import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.file.repository.BoardImageRepository;
import com.soondae.camp.member.repository.MemberRepository;
import com.soondae.camp.reply.dto.ReplyInsertDTO;
import com.soondae.camp.reply.entity.Reply;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardImageRepository boardImageRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long replyRegister(ReplyInsertDTO replyInsertDTO) {

        Reply reply = replyInsertDTOtoEntity(replyInsertDTO);

        replyRepository.save(reply);

        return reply.getRno();
    }
}
