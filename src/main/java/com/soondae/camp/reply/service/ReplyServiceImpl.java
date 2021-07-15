package com.soondae.camp.reply.service;

import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.file.repository.BoardImageRepository;
import com.soondae.camp.member.repository.MemberRepository;
import com.soondae.camp.reply.dto.ReplyInsertDTO;
import com.soondae.camp.reply.dto.ReplyUpdateReqDTO;
import com.soondae.camp.reply.entity.Reply;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void replyRemove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public Long replyUpdate(ReplyUpdateReqDTO replyUpdateReqDTO) {
        Optional<Reply> result = replyRepository.findById(replyUpdateReqDTO.getRno());
        if(result.isPresent()) {
            Reply reply = result.get();
            reply.changeReply(replyUpdateReqDTO.getRtext());
            replyRepository.save(reply);
            return reply.getRno();
        }
        return null;
    }
}
