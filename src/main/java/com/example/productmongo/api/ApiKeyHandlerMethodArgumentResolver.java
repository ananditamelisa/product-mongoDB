package com.example.productmongo.api;

import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ApiKeyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return ApiKey.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter, BindingContext bindingContext, ServerWebExchange serverWebExchange) {
        return Mono.fromSupplier(()->{
            String value = serverWebExchange.getRequest().getHeaders().getFirst("Api-Key");
            if(value==null){
                throw new ApiKeyException("Errors");
            }else{
                return new ApiKey(value);
            }
        });
    }
}
