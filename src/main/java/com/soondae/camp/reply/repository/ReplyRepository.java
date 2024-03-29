package com.soondae.camp.reply.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> getByBoard(Board board, Pageable pageable);

    Set<Reply> getByBoard(Board board);

    @Query("select r, r.member.mnickName " +
            " from Reply r " +
            " where r.board = :board and r.rno > 0")
    Set<Reply> getByBoardWithMember(Board board);

}
