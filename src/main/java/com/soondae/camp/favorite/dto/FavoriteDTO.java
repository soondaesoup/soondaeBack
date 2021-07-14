package com.soondae.camp.favorite.dto;

import com.soondae.camp.board.entity.Board;
import lombok.*;

import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteDTO {

    private Long fno;

    private boolean fstatus = false;

    private Board board;

    public void changeFavorite(boolean fstatus) {
        this.fstatus = fstatus;
    }

}
