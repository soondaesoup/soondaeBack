package com.soondae.camp.file.controller;

import com.soondae.camp.file.dto.BoardImageDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

@CrossOrigin
@RestController
@RequestMapping("/files")
@Log4j2
public class BoardImageController {

    @Value("C:\\ztemp")
    private String path;

    @Value("C:\\tempDB")
    private String pathDB;

    @GetMapping(value = "/check")
    public @ResponseBody String testCheck() {
        return "abc";
    }

    @ResponseBody
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoardImageDTO> upload(MultipartFile[] files) {

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
                    .build());
        } // end loop

        log.info(result);
        return result;
    }

}
