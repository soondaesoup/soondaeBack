package com.soondae.camp.board.repository.dynamic;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.soondae.camp.board.entity.Board;
import com.soondae.camp.board.entity.QBoard;
import com.soondae.camp.favorite.entity.QFavorite;
import com.soondae.camp.file.entity.QBoardImage;
import com.soondae.camp.member.entity.QMember;
import com.soondae.camp.reply.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchRepoImpl extends QuerydslRepositorySupport implements BoardSearchRepo{

    public BoardSearchRepoImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> getSearchList(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QFavorite favorite = QFavorite.favorite;
        QBoardImage boardImage = QBoardImage.boardImage;
        QMember member = QMember.member;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.leftJoin(favorite).on(favorite.board.eq(board));
        query.leftJoin(boardImage).on(boardImage.board.eq(board));
        query.innerJoin(member).on(member.mno.eq(board.member.mno));
        JPQLQuery<Tuple> tuple = query.select(board, reply.countDistinct(), favorite.countDistinct(), boardImage, member);

        if(keyword != null && type != null && keyword.trim().length() > 0) {
            BooleanBuilder condition =new BooleanBuilder();
            String[] typeArr = type.split("");
            for (String t: typeArr) {
                if(t.equals("t")) {
                    condition.or(board.btitle.contains(keyword));
                }
            }
            tuple.where(condition);
        }

        tuple.where(board.bno.gt(0L));
        tuple.groupBy(board);
        tuple.orderBy(board.bno.desc());
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());
        List<Tuple> tupleList = tuple.fetch();
        List<Object[]> arrList = tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());
        long totalCount = tuple.fetchCount();
        return new PageImpl<>(arrList, pageable, totalCount);
    }

    @Override
    public Object[] getOneBoardWithFavorite(Long bno) {
        QBoard board = QBoard.board;
        QFavorite favorite = QFavorite.favorite;
        JPQLQuery<Board> query = from(board);
        query.leftJoin(favorite).on(favorite.board.eq(board));
        JPQLQuery<Tuple> tuple = query.select(board, favorite.countDistinct());
        tuple.where(board.bno.eq(bno));
        Tuple tupleList = tuple.fetchFirst();
        Object[] res = tupleList.toArray();
        return res;
    }



}
