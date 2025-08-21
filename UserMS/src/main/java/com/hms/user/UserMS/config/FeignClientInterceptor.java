package com.hms.user.UserMS.config;

import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

@Override
public void apply(feign.RequestTemplate template){
    template.header("X-Seret-Key", "SECRET");
}
    
}
