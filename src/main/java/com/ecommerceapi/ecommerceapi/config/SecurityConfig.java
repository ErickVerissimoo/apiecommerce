package com.ecommerceapi.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.filter.JwtFilterAuthentication;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserRepository repository) {
        return http.csrf(CsrfConfigurer::disable)
        .addFilterBefore(new JwtFilterAuthentication(), UsernamePasswordAuthenticationFilter.class)
        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        
        .authorizeHttpRequests(c -> c.requestMatchers("/auth/**", "/public/**", "/home/**")
        .permitAll()
        .anyRequest().authenticated())
        .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
    @Bean
@SneakyThrows
    public AuthenticationManager manager(AuthenticationConfiguration conf){
        return conf.getAuthenticationManager();

    }
    @Bean 
    public UserDetailsService userD(UserRepository repository){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               var user = repository.findByEmail(username).orElseThrow();
                boolean isadm = user.isAdmin();

                return isadm? User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(Role.ADMIN.toString(), Role.USER.toString())
                .build() : User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(Role.USER.toString())
                .build();
            }
        };
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
   
}
