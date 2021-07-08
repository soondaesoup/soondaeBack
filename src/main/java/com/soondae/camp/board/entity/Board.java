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
    private String bcategory;
    private String btitle;
    private String bcontent;
    private String bprice;
    private LocalDateTime bregDate;
    private LocalDateTime bmodDate;

    public void changeValue(String bcontent,String bprice,String btitle,String bcategory){
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bcategory = bcategory;
        this.bprice = bprice;
    }

}
