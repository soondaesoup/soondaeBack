package com.soondae.camp.file.service;

import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardImageService {

    List<BoardImageDTO> imageUpload(MultipartFile[] files);

    String imageRemove(String fuuid);

//    default BoardImage listDTOtoEntity(List<BoardImageDTO> boardImageDTOList) {
//        BoardImage boardImage = boardImageDTOList.stream().map(boardImageDTO -> {
//            BoardImage.builder()
//                    .fuuid(boardImageDTO.getFuuid())
//                    .fname(boardImageDTO.getFname())
//                    .fmain(boardImageDTO.isFmain())
//                    .build();
//        });
//    }
}
