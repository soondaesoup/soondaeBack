package com.soondae.camp.member.filter;

import com.soondae.camp.member.service.MemberService;
import com.soondae.camp.member.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    @Autowired
    private MemberService memberService;

    public CheckFilter(String pattern) {
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
        this.jwtUtil = new JWTUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("====Start Check!!====");
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
        String tokenValue = request.getHeader("Authorization");
        String memail = null;
        log.info("====What is tokenValue-->"+tokenValue+"====");

        String jwtStr = tokenValue.substring(7); // Baerer 6글자+스페이스바까지 해서 7글자 잘라내고 뒤에 토큰값

        try {

        memail = jwtUtil.validateAndExtract(jwtStr); // jwt token값 검사
        log.info("===Check Result-->"+memail+"==="); // 검사결과 -> true? / false?
        log.info("===============================");
        }catch (Exception e) {
            log.error("===Error!!-->"+e.getMessage()+"===");
            makeErrorMessage(response, e);
            return;
        }
        checkSecurityContext(memail, request);
        filterChain.doFilter(request, response); // 다음 필터로 보냄.
    }

    private void checkSecurityContext(String memail, HttpServletRequest request) {
        Authentication beforeAuth = SecurityContextHolder.getContext().getAuthentication();
        log.info("===Check BeforeAuth"+beforeAuth+"===");
        if(beforeAuth == null) {
            UserDetails userDetails = memberService.loadUserByUsername(memail);
            log.info("===Check Email -->"+userDetails+"===");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            ); // 새로 등록할 장부를 만들어둠!
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // 장부에 작성함
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 장부(authenticationToken)을  캐비넷에 등록한다!
        }
    }

    private void makeErrorMessage(HttpServletResponse response, Exception exception){
        try {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String content = "{\"msg\" : \""+ exception.getClass().getSimpleName()+"\"}";
            response.getWriter().println(content);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
