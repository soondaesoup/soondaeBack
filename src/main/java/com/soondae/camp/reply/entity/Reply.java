package com.soondae.camp.reply.entity;

import com.soondae.camp.board.entity.Board;
import com.soondae.camp.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = {"board", "member"})
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @Column(nullable = false)
    private String rtext;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime rregDate;
    @LastModifiedDate
    private LocalDateTime rmodDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeReply(String rtext){
        this.rtext = rtext;
    }

}


