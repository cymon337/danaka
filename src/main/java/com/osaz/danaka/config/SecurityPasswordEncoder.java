package com.osaz.danaka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityPasswordEncoder {
    @Bean
    public PasswordEncoder Encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
