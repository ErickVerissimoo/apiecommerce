package com.ecommerceapi.ecommerceapi.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Value("${public.key}")
    private RSAPublicKey publick;
    @Value("${private.key}")
    private RSAPrivateKey privatek;
    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http.csrf(CsrfConfigurer::disable)   
             .authorizeHttpRequests(c -> c.requestMatchers("/auth/**", "/public/**").permitAll()
             .anyRequest().authenticated())

        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        
        .oauth2ResourceServer(c -> c.jwt(j -> j.jwtAuthenticationConverter(converter())))
        
        .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
  @Bean
   public JwtDecoder decoder() {
    return NimbusJwtDecoder.withPublicKey(publick).build();
   }

   @Bean
   public JwtEncoder encoder(){
    JWK jwk = new RSAKey.Builder(publick).privateKey(privatek)
    .build();
    var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
   }
 
   @Bean
   public JwtAuthenticationConverter converter(){
    JwtGrantedAuthoritiesConverter conv = new JwtGrantedAuthoritiesConverter();
    conv.setAuthorityPrefix("");
    conv.setAuthoritiesClaimName("role");
    JwtAuthenticationConverter authentication = new JwtAuthenticationConverter();
    authentication.setJwtGrantedAuthoritiesConverter(conv);
    return authentication;
   }
   @Bean
   @SneakyThrows
   public AuthenticationManager manager(AuthenticationConfiguration conf) {
    return conf.getAuthenticationManager();
   }
}
