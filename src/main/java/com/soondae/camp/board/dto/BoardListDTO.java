package com.soondae.camp.board.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListDTO {

    // 보드 DTO에 있는것들 다
    private BoardDTO boardDTO;
    private String fuuid;
    private String fname;
    
    private long favoriteCount;
    private long replyCount;

}
