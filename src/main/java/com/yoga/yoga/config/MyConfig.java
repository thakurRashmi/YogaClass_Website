package com.yoga.yoga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class MyConfig {
    
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/**").permitAll()
        )
                // .formLogin(withDefaults());
                .formLogin(formLogin -> formLogin
                                .loginPage("/signin")
                                .loginProcessingUrl("/dologin")
                                .defaultSuccessUrl("/afterlogin")
                                .permitAll()
                ).csrf(csrf -> csrf.disable());
                        // .logout(withDefaults()
                        // .logout(logout -> logout
                        // .logoutUrl("/logout")
                        // .logoutSuccessUrl("/signin")
                        // .deleteCookies("remember-me")
                        
        // http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
        //         .requestMatchers("/user/**").hasRole("USER")
        //         .requestMatchers("/**").permitAll().and().formLogin(withDefaults()).csrf(csrf -> csrf.disable());
        // http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
        //     .requestMatchers("/user/**").hasRole("USER")
        //     .requestMatchers("/**").permitAll().and().formLogin();
        return http.build();
    }
    
}
