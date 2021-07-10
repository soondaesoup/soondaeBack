package com.soondae.camp.board.repository.dynamic;

import com.soondae.camp.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardSearchRepo {

    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);

    Object[] getOneBoardWithFavorite(Long bno);

}
