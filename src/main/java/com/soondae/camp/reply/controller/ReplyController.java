package com.soondae.camp.reply.controller;

import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply/register")
    public ResponseEntity<ReplyDTO> register(@RequestBody ReplyDTO replyDTO) {
        replyService.register(replyDTO);
        return ResponseEntity.ok(replyDTO);
    }

}
