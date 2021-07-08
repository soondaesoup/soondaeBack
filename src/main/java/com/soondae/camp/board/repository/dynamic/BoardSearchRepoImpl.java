package com.soondae.camp.board.repository.dynamic;

import com.soondae.camp.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardSearchRepoImpl extends QuerydslRepositorySupport implements BoardSearchRepo{

    public BoardSearchRepoImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> getSearchList(String type, String keyword, Pageable pageable) {
        return null;
    }
}
