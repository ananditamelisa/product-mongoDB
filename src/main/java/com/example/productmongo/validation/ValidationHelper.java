package com.example.productmongo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationHelper {
    private Validator validator;

    @Autowired
    public ValidationHelper(Validator validator) {
        this.validator = validator;
    }

    public<T> Mono<T> validate(T request){
        return Mono.fromSupplier(()->{
            Set<ConstraintViolation<T>> violations = validator.validate(request);
            if(violations.isEmpty()){
                //sukses
                return request;
            }else{
                //gagal
                violations.forEach(violation->{
                    violation.getMessage();
                });
                throw new IllegalArgumentException("Validation Error");
            }
        });
    }
}
