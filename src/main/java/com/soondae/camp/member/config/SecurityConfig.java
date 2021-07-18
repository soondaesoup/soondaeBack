package com.soondae.camp.member.config;

import com.soondae.camp.member.filter.CheckFilter;
import com.soondae.camp.member.filter.LoginFilter;
import com.soondae.camp.member.filter.RefreshFilter;
import com.soondae.camp.member.handler.CustomAccessDeniedHandler;
import com.soondae.camp.member.handler.LoginFailHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        log.info("===Security Config===");
        http.addFilterBefore(checkFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(refreshFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user00")
//                .password(passwordEncoder().encode("1111"))
//                .authorities("ROLE_USER");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CheckFilter checkFilter() {
        return new CheckFilter("/api/board/register");
    }

    @Bean
    public RefreshFilter refreshFilter() {
        return new RefreshFilter("/refresh");
    }

    @Bean
    public LoginFilter loginFilter() throws Exception{
        LoginFilter loginFilter = new LoginFilter("/login", authenticationManager());
        loginFilter.setAuthenticationFailureHandler(new LoginFailHandler());
        return loginFilter;
    }
}
