package com.soondae.camp.file.entity;

import com.soondae.camp.board.entity.Board;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fregDate;
    @UpdateTimestamp
    private LocalDateTime fmodDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
