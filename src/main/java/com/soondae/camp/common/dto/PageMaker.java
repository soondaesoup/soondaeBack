package com.soondae.camp.common.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageMaker {

    // 현재 페이지 번호
    private int page;
    // 한페이지당 갯수
    private int size;
    // 전체 리스트 갯수
    private int totalCount;
    // 이전
    private boolean prev;
    // 다음
    private boolean next;
    // 페이지 번호 리스트
    private List<Integer> pageNumberList;
    
    // 생성자
    public PageMaker(int page, int size, int totalCount) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

        int totalPage = (int) (Math.ceil(totalCount/(double)size));
        int tempEnd = (int) (Math.ceil(page/10.0) * 10);
        int start = tempEnd - 9;
        int end = totalPage > tempEnd ? tempEnd : totalPage;
        prev = start > 1;
        next = totalPage > tempEnd;
        pageNumberList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }


}
