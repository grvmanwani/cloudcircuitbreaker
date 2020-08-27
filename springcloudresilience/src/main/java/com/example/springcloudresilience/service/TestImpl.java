package com.example.springcloudresilience.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestImpl implements Test {

  @Autowired private CircuitBreakerFactory circuitBreakerFactory;

  @Override
  public int getData() {
    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("postAuctionCircuitbreaker");

    return circuitBreaker.run(() ->
            data(), throwable -> fallBack(throwable));
  }

  int data(){
    try{
      Thread.sleep(10000);
    }catch(Exception e){

    }
    return 10;
  }

  public int fallBack(Throwable e) {
    System.out.println(e);
    return 1;
  }
}
