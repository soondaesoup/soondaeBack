package com.soondae.camp.favorite.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tbl_favorite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "board")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
