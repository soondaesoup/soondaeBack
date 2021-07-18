package com.soondae.camp.member.filter;

import com.google.gson.Gson;
import com.soondae.camp.member.entity.MemberRefreshToken;
import com.soondae.camp.member.repository.MemberRefreshTokenRepository;
import com.soondae.camp.member.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
public class RefreshFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher antPathMatcher;
    private JWTUtil jwtUtil;

    @Autowired
    private MemberRefreshTokenRepository memberRefreshTokenRepository;


    public RefreshFilter(String pattern) {
        this.pattern = pattern;
        this.antPathMatcher = new AntPathMatcher();
        this. jwtUtil = new JWTUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("===Here is RefreshFilter===");
        String requestURI = request.getRequestURI();
        if(antPathMatcher.match(pattern, requestURI)== false) {
            filterChain.doFilter(request, response);
            return;
        }
        String refreshValue = request.getParameter("refresh");
        Optional<MemberRefreshToken> result = memberRefreshTokenRepository.findByRefreshStr(refreshValue);
        MemberRefreshToken refreshToken = result.get();
        String memail = refreshToken.getMemail();
        try {
            String jwtStr = jwtUtil.generateToken(memail);
            String refreshStr = "" + System.currentTimeMillis();
            Map<String, Object> map = new HashMap<>();
            map.put("TOKEN", jwtStr);
            map.put("REFRESH TOKEN", refreshStr);
            long expiredDate = System.currentTimeMillis() + (1000*60*60*30);
            MemberRefreshToken memberRefreshToken = MemberRefreshToken.builder()
                    .memail(memail)
                    .refreshStr(refreshStr)
                    .expiredDate(expiredDate)
                    .build();
            memberRefreshTokenRepository.save(memberRefreshToken);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Gson gson = new Gson();
            String str = gson.toJson(map);
            response.getWriter().println(str);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Gson gson = new Gson();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Message", "Login Fail.......");
            errorMap.put("ErrorCode", e.getClass().getSimpleName());
            String str = gson.toJson(errorMap);
            log.info(str);
            response.getWriter().println(str);
            e.printStackTrace();
        }
    }
}
