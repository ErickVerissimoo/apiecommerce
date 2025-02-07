package com.ecommerceapi.ecommerceapi.filter;

import java.util.Optional;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.service.JwtServiceFactory;
import com.ecommerceapi.ecommerceapi.util.SimpleJwtDecoder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
@RequiredArgsConstructor
public class JwtFilterAuthentication extends OncePerRequestFilter {
@SneakyThrows
    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        {
            String token = Optional.ofNullable(WebUtils.getCookie(request, "Bearer").getValue()).orElse(null);
            if(token != null && SimpleJwtDecoder.isAdmin(token)){

                JwtService service = JwtServiceFactory.getJwtService(Role.ADMIN);
                if(service.isTokenValid(token)){
                    filterChain.doFilter(request, response);
                } else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                    return;
                }

            } if(token !=null && !SimpleJwtDecoder.isAdmin(token)){
                JwtService service = JwtServiceFactory.getJwtService(Role.USER);
                if(service.isTokenValid(token)){
                    filterChain.doFilter(request, response);
                } else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                    return;
                }
            }
    
    filterChain.doFilter(request, response);
}

}
