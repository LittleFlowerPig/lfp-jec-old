package com.lfp.jec.gateway.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//API网关 - zuul
@EnableZuulProxy
public class JecGatewayBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecGatewayBaseApplication.class, args);
	}
}
