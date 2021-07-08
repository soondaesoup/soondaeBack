package com.soondae.camp.board.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.dynamic.BoardSearchRepo;
import com.soondae.camp.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearchRepo {



}
