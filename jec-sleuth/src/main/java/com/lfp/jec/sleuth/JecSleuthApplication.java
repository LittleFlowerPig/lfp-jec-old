package com.lfp.jec.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
//微服务 - 注册服务
@EnableEurekaClient
//链路追踪 - 服务端
@EnableZipkinServer
public class JecSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecSleuthApplication.class, args);
	}
}
