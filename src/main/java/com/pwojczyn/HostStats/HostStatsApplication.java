package com.pwojczyn.HostStats;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@EntityScan(
		basePackageClasses = {HostStatsApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class HostStatsApplication {


	public static void main(String[] args) {
		SpringApplication.run(HostStatsApplication.class, args);
	}



}
