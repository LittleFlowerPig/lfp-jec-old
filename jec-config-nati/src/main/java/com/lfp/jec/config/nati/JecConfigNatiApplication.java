package com.lfp.jec.config.nati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//配置服务 - 服务端
@EnableConfigServer
public class JecConfigNatiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecConfigNatiApplication.class, args);
	}
}
