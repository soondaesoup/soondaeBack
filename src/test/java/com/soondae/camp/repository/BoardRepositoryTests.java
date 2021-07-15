package com.soondae.camp.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.member.entity.Member;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 5000).forEach(value -> {
            long bno = (int) (Math.random()*100) +1;
            Member member = Member.builder()
                    .mno(bno)
                    .mnickName("김수현")
                    .build();
            Board board = Board.builder()
                    .member(member)
                    .btitle("맥북프로 팝니다."+value)
                    .bcontent("공짜로 드립니다.")
                    .bprice("공짜라니까")
                    .bcategory("팝니다")
                    .bstate(false)
                    .bmodDate(LocalDateTime.now())
                    .bregDate(LocalDateTime.now())
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testRead(){
        Optional<Board> result = boardRepository.findById(8L);
        result.ifPresent(board -> {
            log.info(board);
            log.info(board.getMember());
        });
    }

    @Test // 실제 update
    public void testUpdate(){
        Optional<Board> board = boardRepository.findById(10L);
        log.info(board);
        board.ifPresent(board1 -> {
            board1.changeValue("맥북 공짜 아닙니다.","2500000","맥북","팝니다.");
            boardRepository.save(board1);
        });
    }

    @Test // delete지만 사실은 update
    public void testDelete() {
        Optional<Board> result = boardRepository.findById(12L);
        result.ifPresent(board -> {
            board.deleteBoard(true);
            boardRepository.save(board);
        });
    }

    

    @Test // 최신순
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info(result.getContent());
    }

    @Test // 검색 + 페이징
    public void testPagingSearch() {
        Pageable pageable = PageRequest.of(0, 10);
        String keyword = "8";
        String type = "t";
        Page<Object[]> result = boardRepository.getSearchList(type, keyword, pageable);
        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));
    }


}
