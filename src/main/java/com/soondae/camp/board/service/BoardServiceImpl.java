package com.soondae.camp.board.service;

import com.soondae.camp.board.dto.BoardDTO;
import com.soondae.camp.board.dto.BoardDetailDTO;
import com.soondae.camp.board.dto.BoardListDTO;
import com.soondae.camp.board.dto.BoardListRequestDTO;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.repository.BoardRepository;
import com.soondae.camp.board.repository.dynamic.BoardSearchRepo;
import com.soondae.camp.common.dto.DetailResponseDTO;
import com.soondae.camp.common.dto.ListResponseDTO;
import com.soondae.camp.common.dto.PageMaker;
import com.soondae.camp.file.dto.BoardImageDTO;
import com.soondae.camp.file.entity.BoardImage;
import com.soondae.camp.file.repository.BoardImageRepository;
import com.soondae.camp.member.entity.Member;
import com.soondae.camp.member.repository.MemberRepository;
import com.soondae.camp.reply.dto.ReplyDTO;
import com.soondae.camp.reply.entity.Reply;
import com.soondae.camp.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    private final BoardImageRepository boardImageRepository;

    private final MemberRepository memberRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Map<String, Object> entityMap = dtoToEntity(boardDTO);
        Board board = (Board) entityMap.get("board");
        List<BoardImage> boardImages = (List<BoardImage>) entityMap.get("boardImages");
        boardRepository.save(board);
        boardImages.forEach(boardImage -> {
            boardImageRepository.save(boardImage);
        });
        return board.getBno();
    }

    @Override
    public ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO boardListRequestDTO) {
        log.info("====================================================================== getList");
        Pageable pageable = boardListRequestDTO.getPageable();
        Page<Object[]> result = boardRepository.getSearchList(boardListRequestDTO.getType(), boardListRequestDTO.getKeyword(), pageable);
        List<BoardListDTO> boardListDTOS = result.getContent().stream().map(objects -> arrToDTO(objects)).collect(Collectors.toList());
        log.info("================================================================== result"+boardListDTOS);
        PageMaker pageMaker = new PageMaker(boardListRequestDTO.getPage(), boardListRequestDTO.getSize(), (int) result.getTotalElements());
        return ListResponseDTO.<BoardListDTO>builder()
                .listRequestDTO(boardListRequestDTO)
                .boardListDTO(boardListDTOS)
                .pageMaker(pageMaker)
                .build();
    }

    @Override
    public BoardDetailDTO getOne(Long bno) {
        Object[] boardWithFavorite = boardRepository.getOneBoardWithFavorite(bno);
        Set<Reply> replies = replyRepository.getByBoard((Board) boardWithFavorite[0]);
        Set<BoardImage> images = boardImageRepository.getByBoard((Board) boardWithFavorite[0]);


        BoardDTO boardDTO = entityToDTO((Board) boardWithFavorite[0]);
        Set<ReplyDTO> replyDTOS = replies.stream().map(reply -> ReplyEntityToDTO(reply)).collect(Collectors.toSet());
        Set<BoardImageDTO> boardImageDTOS =  images.stream().map(boardImage -> ImageEntityToDTO(boardImage)).collect(Collectors.toSet());

        BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder()
                .boardDTO(boardDTO)
                .replyDTO(replyDTOS)
                .boardImageDTO(boardImageDTOS)
                .favoriteCount((Long) boardWithFavorite[1])
                .build();
        return boardDetailDTO;
    }

    @Override
    public BoardDTO modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        if (result.isPresent()) {
            Board board = result.get();
            board.changeContent(boardDTO.getBtitle(), boardDTO.getBcontent(), boardDTO.getBprice(), boardDTO.getBcategory());
            boardRepository.save(board);
            return entityToDTO(board);
        }
        return null;
    }

    @Override
    public Long deleteBoard(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        if(result.isPresent()) {
            Board board = result.get();
            board.deleteBoard(true);
            boardRepository.save(board);
            return board.getBno();
        }
        return null;
    }


}
