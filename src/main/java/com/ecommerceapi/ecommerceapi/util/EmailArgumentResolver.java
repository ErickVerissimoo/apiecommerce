package com.ecommerceapi.ecommerceapi.util;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class EmailArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    @Nullable
    public Object resolveArgument(
            MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory) {
                
        Object attribute = webRequest.getAttribute("email", NativeWebRequest.SCOPE_REQUEST);

        if (attribute != null && parameter.getParameterType().isAssignableFrom(attribute.getClass())) {
            
            return attribute;
        }

        return null; 
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }
}