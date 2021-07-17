package com.soondae.camp.member.filter;

import com.google.gson.Gson;
import com.soondae.camp.member.dto.LoginDTO;
import com.soondae.camp.member.dto.MemberDTO;
import com.soondae.camp.member.entity.MemberRefreshToken;
import com.soondae.camp.member.repository.MemberRefreshTokenRepository;
import com.soondae.camp.member.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.persistence.Id;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private MemberRefreshTokenRepository memberRefreshTokenRepository;

    public LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("===got it!===");
        log.info("===attempt Login===");
        String jsonStr = request.getReader().readLine(); // 한줄로 써야 하는 이유
        Gson gson = new Gson();
        LoginDTO loginDTO = gson.fromJson(jsonStr, LoginDTO.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getMemail(), loginDTO.getMpw());
        Authentication authentication = this.getAuthenticationManager().authenticate(authenticationToken);
        log.info("===CheckManager--> "+this.getAuthenticationManager().getClass().getName()+"===");
        log.info("===CheckAuthentication--> "+authentication+"===");
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("===Success Login===");
        Object principal = authResult.getPrincipal();
        log.info("===What is Principal???"+principal+"===");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, Object> map = new HashMap<>();
        String memail = ((MemberDTO)principal).getUsername(); // 여기서 username 가져오고
        log.info("===This is ((MemberDTO)principal).getUsername" + memail+"===");

        try {
            log.info("===getToken Start!===");
            String jwt = new JWTUtil().generateToken(memail);
            String refreshStr = "" + System.currentTimeMillis(); // 여기서 refreshtoken 붙여주고
            map.put("TOKEN", jwt);
            map.put("REFRESH TOKEN", refreshStr); // map으로 token값과 refreshToken 값을 map에 저장
            long expiredDate = System.currentTimeMillis() + (1000*60*60*30);
            MemberRefreshToken refreshToken = MemberRefreshToken.builder()
                    .memail(memail)
                    .refreshStr(refreshStr)
                    .expiredDate(expiredDate)
                    .build();
            memberRefreshTokenRepository.save(refreshToken); // DB에 저장

            Gson gson = new Gson();
            String str = gson.toJson(map);
            response.getWriter().println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
