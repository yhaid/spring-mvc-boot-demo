package com.dyh.myspringbootdemo.controller;

import com.dyh.myspringbootdemo.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String getUserInfo(@MyRequestBody Map<String, Object> data) {
        return userService.getUserInfo(data);
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface MyRequestBody {

    }

    public static class MyHandlerMethodArgumentResolverDecorator implements HandlerMethodArgumentResolver, ApplicationContextAware {

        private RequestResponseBodyMethodProcessor delegate;

        private ApplicationContext applicationContext;

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(MyRequestBody.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            if (delegate == null) {
                delegate = (RequestResponseBodyMethodProcessor) applicationContext.getBean(RequestMappingHandlerAdapter.class).getArgumentResolvers().stream().filter(a -> a instanceof RequestResponseBodyMethodProcessor).findFirst().get();
            }
            Object resolveArgument = delegate.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
            if (resolveArgument instanceof Map) {
                ((Map) resolveArgument).put("timestamp", System.currentTimeMillis());
            }
            return resolveArgument;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }

}
