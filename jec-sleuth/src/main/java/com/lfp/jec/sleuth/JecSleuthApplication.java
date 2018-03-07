package com.lfp.jec.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
//链路追踪 - 服务端
@EnableZipkinServer
public class JecSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecSleuthApplication.class, args);
	}
}
