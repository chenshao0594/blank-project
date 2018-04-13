
package com.blank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class PortalApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(PortalApplication.class);
		springApplication.addListeners();
		springApplication.run(args);
	}
}
