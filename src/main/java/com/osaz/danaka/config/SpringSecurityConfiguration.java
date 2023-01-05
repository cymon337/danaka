package com.osaz.danaka.config;

import com.osaz.danaka.member.model.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;


@EnableWebSecurity//spring security 를 적용한다는 Annotation
@Configuration
public class SpringSecurityConfiguration {

    private final MemberService memberService;

    @Autowired
    public SpringSecurityConfiguration(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers("/css/**", "images/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        Map<String, List<String>> permitListMap = memberService.getPermitListMap();
        //getPermitListMap 만들어서 권한별로 접근가능한 url 목록을 받아오자 AuthenticationService, 와 Impl 에 가서 추가구현하자
        List<String> adminPermitList = permitListMap.get("adminPermitList");
        List<String> memberPermitList = permitListMap.get("memberPermitList");

        adminPermitList.forEach(url -> System.out.println("admin permit list : " + url));
        memberPermitList.forEach(url -> System.out.println("member permit list : " + url));

        http.csrf().disable()
                 .authorizeHttpRequests()
                .antMatchers("/member/login", "/member/id","/member/password", "/member/signup").permitAll()
                .antMatchers("/**").authenticated()
                .and().formLogin()
                .loginPage("/member/login")
                .successForwardUrl("/") //로그인 성공시 이동할 경로 설정
                //.failureForwardUrl("/error/login") //에러가 발생했을때 포워딩할 경로 기술
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");



        return http.build();
    }

}