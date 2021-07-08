package com.soondae.camp.board.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearchRepo {

    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);

}
