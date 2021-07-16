package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.*;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.common.dto.DetailResponseDTO;
import com.soondae.camp.common.dto.ListResponseDTO;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.member.dto.BoardDetailWithMemberDTO;
import com.soondae.camp.member.dto.MemberDTO;
import com.soondae.camp.member.dto.UserDTO;
import com.soondae.camp.member.entity.Member;
import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.entity.Reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BoardService {

    Long register(BoardInsertDTO boardInsertDTO);

    ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO boardListRequestDTO);

    BoardDetailDTO getOne(Long bno);

    BoardDTO modify(BoardDTO boardDTO);

    Long deleteBoard(Long bno);


    default BoardListDTO arrToDTO(Object[] arr) {
        Board board = (Board) arr[0];
        long favoriteCount = (long) arr[1];
        long replyCount = (long) arr[2];
        BoardImage boardImage = (BoardImage) arr[3];
        Member member = (Member) arr[4];
        return BoardListDTO.builder()
                .boardDTO(entityToDTO(board))
                .favoriteCount(favoriteCount)
                .replyCount(replyCount)
                .boardImageDTO(ImageEntityToDTO(boardImage))
                .userDTO(memberEntityToDTO(member))
                .build();
    }

    default UserDTO memberEntityToDTO(Member member) {
        return UserDTO.builder()
                .mno(member.getMno())
                .mnickName(member.getMnickName())
                .build();
    }

    default BoardDetailWithMemberDTO entityTowithMemberDTO(Member member) {
        return BoardDetailWithMemberDTO.builder()
                .mno(member.getMno())
                .maddress(member.getMaddress())
                .memail(member.getMemail())
                .mphone(member.getMphone())
                .mnickName(member.getMnickName())
                .build();
    }

    default BoardImageDTO ImageEntityToDTO(BoardImage boardImage) {
        if(boardImage == null) {
            return BoardImageDTO.builder()
                    .fuuid("")
                    .fname("")
                    .build();
        }
        return BoardImageDTO.builder()
                .fuuid(boardImage.getFuuid())
                .fname(boardImage.getFname())
                .build();
    }

    default ReplyDTO ReplyEntityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .rtext(reply.getRtext())
                .rwriter(reply.getMember().getMnickName())
                .rregDate(reply.getRregDate())
                .rmodDate(reply.getRmodDate())
                .build();
    }

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .bstate(board.isBstate())
                .bcategory(board.getBcategory())
                .btitle(board.getBtitle())
                .bcontent(board.getBcontent())
                .bprice(board.getBprice())
                .build();
    }

    default Map<String, Object> dtoToEntity(BoardInsertDTO boardInsertDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder().mno(boardInsertDTO.getMno()).build();
        Board board = Board.builder()
                .member(member)
                .bstate(boardInsertDTO.isBstate())
                .bcategory(boardInsertDTO.getBcategory())
                .btitle(boardInsertDTO.getBtitle())
                .bcontent(boardInsertDTO.getBcontent())
                .bprice(boardInsertDTO.getBprice())
                .build();
        entityMap.put("board", board);
        List<BoardImageDTO> imageDTOS = boardInsertDTO.getImageDTOS();
        if(imageDTOS != null && imageDTOS.size()>0) {
            List<BoardImage> boardImages = imageDTOS.stream().map(boardImageDTO -> {
                BoardImage boardImage = BoardImage.builder()
                        .fuuid(boardImageDTO.getFuuid())
                        .fname(boardImageDTO.getFname())
                        .board(board)
                        .build();
                return boardImage;
            }).collect(Collectors.toList());
            entityMap.put("boardImages", boardImages);
        }
        return entityMap;
    }

}