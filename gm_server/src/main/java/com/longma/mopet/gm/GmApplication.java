package com.longma.mopet.gm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.longma.mopet.gm")
@ComponentScan
@ServletComponentScan
@EnableConfigurationProperties
@SpringBootApplication
public class GmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmApplication.class, args);

	}
}
