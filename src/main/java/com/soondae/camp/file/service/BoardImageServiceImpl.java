package com.soondae.camp.file.service;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.file.repository.BoardImageRepository;
import com.soondae.camp.member.repository.MemberRepository;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class BoardImageServiceImpl implements BoardImageService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardImageRepository boardImageRepository;
    private final MemberRepository memberRepository;

    @Value("C:\\ztemp")
    private String path;
    @Value("C:\\tempDB")
    private String pathDB;

    @Override
    public List<BoardImageDTO> imageUpload(MultipartFile[] files, Long bno) {

        List<BoardImageDTO> result = new ArrayList<>();

        for (MultipartFile file:files) {
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            File outFile = new File(path, uuid+"_"+originalFilename);
            File thumbFile = new File(path, "s_"+uuid+"_"+originalFilename);

            try {
                InputStream fin = file.getInputStream();
                Files.copy(fin, outFile.toPath());

                BufferedImage originalImage = ImageIO.read(outFile);
                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .size(150, 150)
                        .asBufferedImage();

                ImageIO.write(thumbnail, "PNG", thumbFile);
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.add(BoardImageDTO.builder()
                    .fuuid(uuid)
                    .fname(originalFilename)
                    .bno(bno)
                    .build());
            
//            log.info("============================= result"+result);
//
//            List<BoardImage> imgList = listDTOtoEntity(result);
//
//            log.info("============================= imgList"+imgList);
//
//            Board board = Board.builder()
//                    .bno(bno)
//                    .build();
//
//            log.info("board=============================="+board);
//
//            imgList.stream().map(boardImage -> {
//                BoardImage boardImageEntity = BoardImage.
//                        builder()
//                        .fuuid(boardImage.getFuuid())
//                        .fname(boardImage.getFname())
//                        .fmain(boardImage.isFmain())
//                        .board(board)
//                        .build();
//                log.info("boardImage.getBoard()================================="+boardImage.getBoard());
//                boardImageRepository.save(boardImageEntity);
//                log.info("boardImageEntity==========================="+boardImageEntity);
//                return boardImageEntity;
//            });
        } // end loop
        return result;
    }

    @Override
    public String imageRemove(String fuuid) {
        String fileName = path+fuuid;
        boolean fileName2 = path.equals(fuuid);
        log.info("=================================="+fileName);
        log.info("=================================="+fileName2);
        return null;
    }
}
