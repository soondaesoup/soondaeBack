package com.soondae.camp.board.entity;

import com.soondae.camp.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "member")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(nullable = false)
    private String btitle;
    @Builder.Default
    private boolean bstate = false;
    @Column(nullable = false)
    private String bcategory;
    @Column(nullable = false)
    private String bcontent;
    @Column(nullable = false)
    private String bprice;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime bregDate;
    @UpdateTimestamp
    private LocalDateTime bmodDate;

    @Builder.Default
    private boolean bdeleted = false;

    @ManyToOne
    private Member member;

    public void changeValue(String bcontent,String bprice,String btitle,String bcategory){
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bcategory = bcategory;
        this.bprice = bprice;
    }

    public void deleteBoard(boolean bdeleted) {
        this.bdeleted = bdeleted;
    }

    public void changeContent(String btitle, String bcontent, String bprice, String bcategory){
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bprice = bprice;
        this.bcontent = bcontent;
    }

}
