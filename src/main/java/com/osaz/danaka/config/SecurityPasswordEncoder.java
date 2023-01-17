package com.osaz.danaka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class SecurityPasswordEncoder {

    // # title : 패스워드 인코더
    // # author : 정근호
    // # description : PasswordEncoder 빈으로 설정하고 꺼내서 쓸수 있게 해준다
    //                 
    @Bean
    public PasswordEncoder Encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}

