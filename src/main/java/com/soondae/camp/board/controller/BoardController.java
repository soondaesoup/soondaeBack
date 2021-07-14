package com.soondae.camp.board.controller;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.dto.BoardDetailDTO;
import com.soondae.camp.board.dto.BoardListDTO;
import com.soondae.camp.board.dto.BoardListRequestDTO;
import com.soondae.camp.board.service.BoardService;
import com.soondae.camp.common.dto.DetailResponseDTO;
import com.soondae.camp.common.dto.ListResponseDTO;
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

    // getList
    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO> getList(BoardListRequestDTO boardListRequestDTO) {
        return ResponseEntity.ok(boardService.getList(boardListRequestDTO));
    }

    // getOne
    @GetMapping("/{bno}")
    public ResponseEntity<BoardDetailDTO> getOne(@PathVariable Long bno) {
        BoardDetailDTO boardDetailDTO = boardService.getOne(bno);
        return ResponseEntity.ok(boardDetailDTO);
    }


    // update
    @PutMapping("/{bno}")
    public ResponseEntity<BoardDTO> modify(@PathVariable Long bno, @RequestBody BoardDTO boardDTO) {
        boardDTO.setBno(bno);
        BoardDTO modfiedDTO = boardService.modify(boardDTO);
        return ResponseEntity.ok(modfiedDTO);

    }

    // delete
    @DeleteMapping("/{bno}")
    public ResponseEntity<Long> delete(@PathVariable Long bno) {
        Long deleteNo = boardService.deleteBoard(bno);
        return ResponseEntity.ok(deleteNo);
    }


}
