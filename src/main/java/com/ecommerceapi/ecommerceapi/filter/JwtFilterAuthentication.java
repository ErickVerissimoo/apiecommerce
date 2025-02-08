package com.ecommerceapi.ecommerceapi.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.service.jwt.JwtServiceFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class JwtFilterAuthentication extends OncePerRequestFilter {
    private JwtServiceFactory factory;
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        var bearerCookie = WebUtils.getCookie(request, "Bearer");
        if (bearerCookie == null || bearerCookie.getValue() == null || bearerCookie.getValue().isEmpty()) {
            filterChain.doFilter(request, response);
          return;
        }

        var token = bearerCookie.getValue();
     
        JwtService service = factory.getJwtService(token);
        if(service.isTokenValid(token)){
            String email = service.extractEmail(token);
            request.setAttribute("email", email);
            filterChain.doFilter(request, response);
            return; 
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inváldo");
            return;
        }
        
       
    }
}