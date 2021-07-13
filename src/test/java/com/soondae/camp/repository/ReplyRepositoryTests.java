package com.soondae.camp.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.reply.entity.Reply;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 5000).forEach(value -> {
            long bno = (int) (Math.random()*100)+1;
            Board board = Board.builder()
                    .bno(bno)
                    .build();
            Reply reply = Reply.builder()
                    .board(board)
                    .rregDate(LocalDateTime.now())
                    .rmodDate(LocalDateTime.now())
                    .rtext("공짜라도 안사")
                    .build();
            replyRepository.save(reply);
        });
    }

    @Test
    public void testUpdate(){
        Optional<Reply> reply = replyRepository.findById(100L);
        reply.ifPresent(reply1 -> {
            reply1.changeReply("저 살게요...");
            replyRepository.save(reply1);
        });
    }

    @Test
    public void testRead(){
        Optional<Reply> result = replyRepository.findById(1L);
        log.info(result);
        result.ifPresent(reply -> log.info(reply));
    }

    @Test
    public void testReplyDelete(){
        replyRepository.deleteById(54L);
    }

    @Test // 보드 1개당 댓글 다가져오기 + 페이징
    public void testPagingByBoard() {
        Pageable pageable = PageRequest.of(0,10);
        Board board = Board.builder().bno(32L).build();
        Object[] boardWithFavorite = boardRepository.getOneBoardWithFavorite(32L);
        Page<Reply> result = replyRepository.getByBoard(board, pageable);
        log.info(result.getContent());
    }


}
