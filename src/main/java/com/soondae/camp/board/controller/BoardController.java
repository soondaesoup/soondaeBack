package com.soondae.camp.board.controller;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    // create
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody BoardDTO boardDTO) {
        Long bno = boardService.register(boardDTO);
        return ResponseEntity.ok(bno);
    }



    // getOne

    // getList

    // update



}
