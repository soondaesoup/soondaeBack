package com.soondae.camp.file.repository;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BoardImageRepository extends JpaRepository<BoardImage, String> {

    Set<BoardImage> getByBoard(Board board);

}
