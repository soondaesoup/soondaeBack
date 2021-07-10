package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.dto.BoardListDTO;
import com.soondae.camp.board.dto.BoardListRequestDTO;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.board.repository.dynamic.BoardSearchRepo;
import com.soondae.camp.common.dto.ListResponseDTO;
import com.soondae.camp.common.dto.PageMaker;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.file.repository.BoardImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

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

    @Override
    public ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO boardListRequestDTO) {
        Pageable pageable = boardListRequestDTO.getPageable();
        Page<Object[]> result = boardRepository.getSearchList(boardListRequestDTO.getType(), boardListRequestDTO.getKeyword(), pageable);
        List<BoardListDTO> boardListDTOS = result.getContent().stream().map(objects -> arrToDTO(objects)).collect(Collectors.toList());
        PageMaker pageMaker = new PageMaker(boardListRequestDTO.getPage(), boardListRequestDTO.getSize(), (int) result.getTotalElements());
        return ListResponseDTO.<BoardListDTO>builder()
                .listRequestDTO(boardListRequestDTO)
                .boardListDTO(boardListDTOS)
                .pageMaker(pageMaker)
                .build();
    }


}
