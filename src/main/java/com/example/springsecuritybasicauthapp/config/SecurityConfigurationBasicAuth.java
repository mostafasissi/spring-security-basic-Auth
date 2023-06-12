package com.example.springsecuritybasicauthapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationBasicAuth {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user1 = User.builder()
                .username("Mike")
                .password(encoder().encode("passw@d"))
                .roles("CAPTAIN","CREW")
                .build();
        UserDetails user2 = User.builder()
                .username("Henrik")
                .password(encoder().encode("p@ssword"))
                .roles("CREW")
                .build();
        return new InMemoryUserDetailsManager(user1 , user2 );
    }
    // creating a list of security filters
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/navigation/**").hasRole("CAPTAIN")
                        .requestMatchers("/cantina/**").hasRole("CREW")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
