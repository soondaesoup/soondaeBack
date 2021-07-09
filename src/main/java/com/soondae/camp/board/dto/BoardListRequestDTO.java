package com.soondae.camp.board.dto;

import com.soondae.camp.common.dto.ListRequestDTO;
import lombok.Data;

@Data
public class BoardListRequestDTO extends ListRequestDTO {

    private String type;

}
