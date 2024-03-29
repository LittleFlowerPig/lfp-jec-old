package com.lfp.jec.balance.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//断路器 - 服务故障
@EnableHystrix
//断路器 - 监控看板
@EnableHystrixDashboard
public class JecBalanceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecBalanceUserApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
