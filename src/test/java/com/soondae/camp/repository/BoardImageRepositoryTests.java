package com.soondae.camp.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.file.repository.BoardImageRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardImageRepositoryTests {

    @Autowired
    private BoardImageRepository boardImageRepository;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1,101).forEach(value -> {
            long bno = (int) (Math.random()*101+1);
            Board board = Board.builder()
                    .bno(bno)
                    .build();
            BoardImage boardImage = BoardImage.builder()
                    .fuuid("9fea1bbb-382a-4177-b483-fd3371cc4e6d_erd"+value)
                    .fname("미팅"+value)
                    .board(board)
                    .build();
            boardImageRepository.save(boardImage);
        });
    }

}
