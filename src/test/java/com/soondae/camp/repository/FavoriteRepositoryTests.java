package com.soondae.camp.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.favorite.entity.Favorite;
import com.soondae.camp.favorite.repository.FavoriteRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FavoriteRepositoryTests {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 5000).forEach(value -> {
            long bno = (int)(Math.random()*4000)+1;
            Board board = Board.builder()
                    .bno(bno)
                    .build();
            Favorite favorite = Favorite.builder()
                    .board(board)
                    .fstatus(true)
                    .build();
            favoriteRepository.save(favorite);
        });
    }

    @Test // 좋아요 수정
    public void testUpdate() {
        Optional<Favorite> result = favoriteRepository.findById(3L);
        result.ifPresent(favorite -> {
            favorite.changeFavorite(false);
            favoriteRepository.save(favorite);
        });
    }



}
