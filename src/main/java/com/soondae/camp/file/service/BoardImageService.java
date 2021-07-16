package com.soondae.camp.file.service;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardImageService {

    List<BoardImageDTO> imageUpload(MultipartFile[] files, Long bno);

    String imageRemove(String fuuid);

    default List<BoardImage> listDTOtoEntity(List<BoardImageDTO> boardImageDTOList) {

//        Board board = Board.builder()
//                .bno(boardImageDTOList.stream().map(boardImageDTO -> {
//                    boardImageDTO.getBno().
//                }))
//                .build();

        List<BoardImage> boardImage = boardImageDTOList.stream().map(boardImageDTO ->

                BoardImage.builder()
                        .fuuid(boardImageDTO.getFuuid())
                        .fname(boardImageDTO.getFname())
                        .fmain(boardImageDTO.isFmain())
                        .board(Board.builder()
                                .bno(boardImageDTO.getBno())
                                .build())
                        .build()
        ).collect(Collectors.toList());

        return boardImage;
    }
}
