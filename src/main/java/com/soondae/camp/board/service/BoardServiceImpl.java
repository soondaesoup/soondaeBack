package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.file.repository.BoardImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final BoardImageRepository boardImageRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Map<String, Object> entityMap = dtoToEntity(boardDTO);
        Board board = (Board) entityMap.get("board");
        List<BoardImage> boardImages = (List<BoardImage>) entityMap.get("boardImages");
        boardRepository.save(board);
        boardImages.forEach(boardImage -> {
            boardImageRepository.save(boardImage);
        });
        return board.getBno();
    }
}
