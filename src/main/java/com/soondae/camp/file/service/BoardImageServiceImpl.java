package com.soondae.camp.file.service;

import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.file.repository.BoardImageRepository;
import com.soondae.camp.member.repository.MemberRepository;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class BoardImageServiceImpl implements BoardImageService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardImageRepository boardImageRepository;
    private final MemberRepository memberRepository;

    @Value("C:\\ztemp")
    private String path;
    @Value("C:\\tempDB")
    private String pathDB;

    @Override
    public String imageRemove(String fuuid) {
        String fileName = path+fuuid;
        boolean fileName2 = path.equals(fuuid);
        log.info("=================================="+fileName);
        log.info("=================================="+fileName2);
        return null;
    }
}
