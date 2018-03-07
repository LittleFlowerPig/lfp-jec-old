package com.lfp.jec.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//断路器 - 监控看板
@EnableHystrixDashboard
//断路器 - 聚合监控
@EnableTurbine
public class JecTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecTurbineApplication.class, args);
	}
}
