package com.example.springcloudresilience.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfigDemo {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> customConfig() {
    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
            .cancelRunningFuture(true)
            .timeoutDuration(Duration.ofSeconds(5))
            .build();
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .minimumNumberOfCalls(5)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .slidingWindowSize(2)
            .build();

    return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
            .timeLimiterConfig(timeLimiterConfig)
            .build(),"postAuctionCircuitbreaker");
  }
}
