package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.dto.BoardDetailDTO;
import com.soondae.camp.board.dto.BoardListDTO;
import com.soondae.camp.board.dto.BoardListRequestDTO;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.common.dto.DetailResponseDTO;
import com.soondae.camp.common.dto.ListResponseDTO;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.entity.Reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO boardListRequestDTO);

    BoardDetailDTO getOne(Long bno);


    default BoardListDTO arrToDTO(Object[] arr) {
        Board board = (Board) arr[0];
        long favoriteCount = (long) arr[1];
        long replyCount = (long) arr[2];
        return BoardListDTO.builder()
                .boardDTO(entityToDTO(board))
                .favoriteCount(favoriteCount)
                .replyCount(replyCount)
                .build();
    }

    default BoardImageDTO ImageEntityToDTO(BoardImage boardImage) {
        return BoardImageDTO.builder()
                .fuuid(boardImage.getFuuid())
                .fname(boardImage.getFname())
                .build();
    }

    default ReplyDTO ReplyEntityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .rtext(reply.getRtext())
                .rwriter(reply.getRwriter())
                .rregDate(reply.getRregDate())
                .rmodDate(reply.getRmodDate())
                .rdeleted(reply.isRdeleted())
                .build();
    }

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .bstate(board.isBstate())
                .bcategory(board.getBcategory())
                .btitle(board.getBtitle())
                .bcontent(board.getBcontent())
                .bprice(board.getBprice())
                .bwriter(board.getBwriter())
                .bregDate(board.getBregDate())
                .bmodDate(board.getBmodDate())
                .build();
    }

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
