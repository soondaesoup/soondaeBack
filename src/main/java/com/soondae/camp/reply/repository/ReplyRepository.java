package com.soondae.camp.reply.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> getByBoard(Board board, Pageable pageable);

}
