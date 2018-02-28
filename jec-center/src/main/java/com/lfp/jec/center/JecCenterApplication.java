package com.lfp.jec.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//微服务 - 注册中心
@EnableEurekaServer
public class JecCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecCenterApplication.class, args);
	}
}
