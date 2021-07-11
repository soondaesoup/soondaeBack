package com.soondae.camp.member.filter;

import com.google.gson.Gson;
import com.soondae.camp.member.dto.LoginDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

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



}
