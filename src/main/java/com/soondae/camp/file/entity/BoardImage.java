package com.soondae.camp.file.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class BoardImage {

    @Id
    private String fuuid;

    @Column(nullable = false)
    private String fname;

    @Builder.Default
    private boolean main=false;

    @ManyToOne
    private Board board;

}
