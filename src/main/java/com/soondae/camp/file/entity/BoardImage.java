package com.soondae.camp.file.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "board")
public class BoardImage {

    @Id
    private String fuuid;

    @Column(nullable = false)
    private String fname;

    @Column
    @Builder.Default
    private boolean fmain=false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime fregDate;
    @LastModifiedDate
    private LocalDateTime fmodDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
