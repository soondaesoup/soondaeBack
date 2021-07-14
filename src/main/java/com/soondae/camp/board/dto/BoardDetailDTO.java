package com.soondae.camp.board.dto;

import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.member.dto.UserDTO;
import com.soondae.camp.reply.dto.ReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {

    private BoardDTO boardDTO;
    private Set<ReplyDTO> replyDTO;

    private Set<BoardImageDTO> boardImageDTO;
    private long favoriteCount;




}
