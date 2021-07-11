package com.soondae.camp.member.filter;

import com.soondae.camp.member.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CheckFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher matcher;
    private JWTUtil jwtUtil;

    public CheckFilter(String pattern) {
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
        this.jwtUtil = new JWTUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("===Start Check!!===");
        // 어떤 URI???를 검사하는가???
        String requestURI = request.getRequestURI();
        boolean matchResult = matcher.match(pattern, requestURI);
        if(matchResult == false) {
            // URI가 맞지 않으면 다음 필터로 패스
            log.info("===Pass!===");
            filterChain.doFilter(request, response);
            return;
        }
        // URI가 맞으면??

    }
}
