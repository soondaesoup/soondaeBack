package com.soondae.camp.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Builder.Default
    private boolean bstate = false;
    @Column(nullable = false)
    private String bcategory;
    @Column(nullable = false)
    private String btitle;
    @Column(nullable = false)
    private String bcontent;
    @Column(nullable = false)
    private String bprice;
    private LocalDateTime bregDate;
    private LocalDateTime bmodDate;
    private boolean bdeleted = false;

    public void changeValue(String bcontent,String bprice,String btitle,String bcategory){
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bcategory = bcategory;
        this.bprice = bprice;
    }

    public void deleteBoard(boolean bdeleted) {
        this.bdeleted = bdeleted;
    }

}
