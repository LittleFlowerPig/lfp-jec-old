package com.lfp.jec.balance.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//负载均衡 - feign
@EnableFeignClients
//断路器 - 监控看板
@EnableHystrixDashboard
//断路器 - 开关监控
@EnableCircuitBreaker
public class JecBalanceResApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecBalanceResApplication.class, args);
	}
}
