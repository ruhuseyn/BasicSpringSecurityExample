package com.example.springsecurity.config;

import org.apache.catalina.UserDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springsecurity.config.UserRole.ADMIN;
import static com.example.springsecurity.config.UserRole.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole(ADMIN.name())
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails rufet = User.builder()
                .username("rufet")
                .password(passwordEncoder.encode("12345"))
                .roles(ADMIN.name())
                .build();
        UserDetails ugur = User.builder()
                .username("ugur")
                .password(passwordEncoder.encode("12345"))
                .roles(USER.name())
                .build();
        return new InMemoryUserDetailsManager(rufet,ugur);
    }
}
