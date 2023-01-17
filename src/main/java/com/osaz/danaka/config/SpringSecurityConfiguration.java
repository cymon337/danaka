package com.osaz.danaka.config;

import com.osaz.danaka.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor //@Configuration 쓰기전에 쓰면 에러남 찾아보기
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final AuthenticationFailureHandler customFailureHandler;
    private final PasswordEncoder passwordEncoder;

    /*   - public 접근 제한자: 단어 뜻 그대로 외부 클래스가 자유롭게 사용할 수 있도록 합니다.
         - protected 접근 제한자: 같은 패키지 또는 자식 클래스에서 사용할 수 있도록 합니다.

       - private 접근 제한자: 단어 뜻 그대로 개인적인 것이라 외부에서 사용될 수 없도록 합니다.*/


    // # title : 인증의 대해서 설정
    // # author : 정근호
    // # description : 인증의 대해서 기본적인 설정을 넣어준다
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(memberService).passwordEncoder(passwordEncoder)
                 .and()
                .inMemoryAuthentication()
                .withUser("user").password("{bcrypt}password").roles("USER")
                .and()
                .withUser("admin").password("{bcrypt}password").roles("USER", "ADMIN");

    }



    // # title : AuthenticationManager 사용
    // # author : 정근호
    // # description : AuthenticationManager를 밖에서도 사용할수 있게 밖으로 꺼내서 빈으로 등록해줌
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }



    // # title : css 파일 이미지 파일 시큐리티 무시되도록 설정
    // # author : 정근호
    // # description :  css 파일 이미지 파일 시큐리티 무시되도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/css/**", "/image/**");
    }

    // # title : 시큐리티 규칙 설정
    // # author : 정근호
    // # description : AuthenticationManagerBuilder는 HttpSecurity에 포함된다 HttpSecurity http를 통해서 위에서 담은걸 쓰고 기본적인
    //                 시큐리티 규칙을 설정해준다
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(  "signUpAction" ,"/member/login", "/member/id","/member/password", "/member/signUp", "/resource/**").permitAll()
                .antMatchers("/product/**").hasAnyRole("ADMIN","USER")
                .antMatchers( "/cart").hasAnyRole("ADMIN","USER")
                .antMatchers( "/package").hasAnyRole("ADMIN","USER")
                .antMatchers("/notice/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll() //모든요청에 접급을 허용하겠다
                .and()
                .formLogin() //폼을 이용해서 로그인을 하겠다
                .loginPage("/member/login") //내가 사용할 로그인 페이지 기술 없으면 시큐리티 페이지가 나옴
                .loginProcessingUrl("/loginAction") // 로그인이 진행 될 url 설정 (loginpage.html의 th:action="@{/loginAction}"를 말함
                //.successForwardUrl("/") //로그인 성공시 이동할 경로 설정
                .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                .defaultSuccessUrl("/")
                .and()
                .logout()   //로그아웃 설정을 시작하겠다
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //AntPathRequestMatcher
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


    }
}
