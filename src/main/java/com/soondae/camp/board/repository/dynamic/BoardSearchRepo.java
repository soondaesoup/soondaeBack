package com.soondae.camp.board.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardSearchRepo {

    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);

}
