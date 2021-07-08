package com.soondae.camp.reply.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "board")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(nullable = false)
    private String rtext;
    @Column(nullable = false)
    private boolean rdeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeReply(String rtext){
        this.rtext = rtext;
    }

}
