package com.soondae.camp.reply.service;

import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.entity.Reply;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO modify(ReplyDTO replyDTO) {
        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());

        if(result.isPresent()){

            Reply entity = result.get();
            entity.changeReply(replyDTO.getRtext());
            replyRepository.save(entity);
            return entityToDTO(entity);
        }
        return null;
    }

}
