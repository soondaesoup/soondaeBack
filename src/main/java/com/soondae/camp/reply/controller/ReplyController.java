package com.soondae.camp.reply.controller;

import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.dto.ReplyInsertDTO;
import com.soondae.camp.reply.dto.ReplyUpdateReqDTO;
import com.soondae.camp.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ReplyInsertDTO replyInsertDTO) {
        replyService.replyRegister(replyInsertDTO);
        return ResponseEntity.ok(200L);
    }

    @DeleteMapping("/remove/{rno}")
    public ResponseEntity<String> replyRemove(@PathVariable Long rno) {
        replyService.replyRemove(rno);
        return ResponseEntity.ok("삭제 완료");
    }

    @PutMapping("/update/{rno}")
    public ResponseEntity<String> replyUpdate(@RequestBody ReplyUpdateReqDTO replyUpdateReqDTO) {
        replyService.replyUpdate(replyUpdateReqDTO);
        return ResponseEntity.ok("수정 완료");
    }

}
