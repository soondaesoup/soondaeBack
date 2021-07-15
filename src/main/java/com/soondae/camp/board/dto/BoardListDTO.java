package com.soondae.camp.board.dto;

import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.member.dto.UserDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListDTO {

    // 보드 DTO에 있는것들 다
    private BoardDTO boardDTO;
    private BoardImageDTO boardImageDTO;
    private UserDTO userDTO;
    private long favoriteCount;
    private long replyCount;

}
