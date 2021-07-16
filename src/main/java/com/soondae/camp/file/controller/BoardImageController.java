package com.soondae.camp.file.controller;

import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.service.BoardImageService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Log4j2
public class BoardImageController {

    private final BoardImageService boardImageService;

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
    public List<BoardImageDTO> upload(MultipartFile[] files, Long bno) {
        log.info("============================="+bno);
        return boardImageService.imageUpload(files, bno);
    }

}
