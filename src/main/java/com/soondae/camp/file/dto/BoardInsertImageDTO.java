package com.soondae.camp.file.dto;


import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardInsertImageDTO {

    private List<BoardImageDTO> boardImageDTO;
    private Long bno;

}
