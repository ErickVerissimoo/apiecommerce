package com.ecommerceapi.ecommerceapi.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ecommerceapi.ecommerceapi.entities.User;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
@Override
public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
   
    return  (User) webRequest.getAttribute("user", NativeWebRequest.SCOPE_REQUEST);
}
@Override
public boolean supportsParameter(MethodParameter parameter) {
    // 
    return User.class.isAssignableFrom(parameter.getParameterType());
}
}
