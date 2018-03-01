package com.lfp.jec.balance.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//负载均衡 - feign
@EnableFeignClients
public class JecBalanceResApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecBalanceResApplication.class, args);
	}
}
