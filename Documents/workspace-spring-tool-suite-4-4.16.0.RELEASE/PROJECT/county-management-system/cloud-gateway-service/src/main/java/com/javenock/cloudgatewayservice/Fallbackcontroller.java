package com.javenock.cloudgatewayservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Fallbackcontroller {

    @RequestMapping("/staff-service-fallback")
    public Mono<String> staffServiceFallback(){
        return Mono.just("Staff service is taking too long to respond, or its down");
    }

    @RequestMapping("/department-service-fallback")
    public Mono<String> departmentServiceFallback(){
        return Mono.just("Department service is taking too long to respond, or its down");
    }
}
