package com.orderpulse.order.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {
    @Bean
    public CircuitBreakerConfigCustomizer customizer() {
        return CircuitBreakerConfigCustomizer.of("orderServiceCB",
        builder -> builder.slidingWindowSize(5).failureRateThreshold(50));
    }
}
