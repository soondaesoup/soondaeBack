package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    default Map<String, Object> dtoToEntity(BoardDTO boardDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .bstate(boardDTO.isBstate())
                .bcategory(boardDTO.getBcategory())
                .btitle(boardDTO.getBtitle())
                .bcontent(boardDTO.getBcontent())
                .bprice(boardDTO.getBprice())
                .bwriter(boardDTO.getBwriter())
                .bregDate(boardDTO.getBregDate())
                .bmodDate(boardDTO.getBmodDate())
                .build();
        entityMap.put("board", board);
        List<BoardImageDTO> imageDTOS = boardDTO.getImageDTOS();
        if(imageDTOS != null && imageDTOS.size()>0) {
            List<BoardImage> boardImages = imageDTOS.stream().map(boardImageDTO -> {
                BoardImage boardImage = BoardImage.builder()
                        .fuuid(boardImageDTO.getFuuid())
                        .fname(boardImageDTO.getFname())
                        .board(board)
                        .build();
                return boardImage;
            }).collect(Collectors.toList());
            entityMap.put("boardImages", boardImages);
        }
        return entityMap;
    }

}
