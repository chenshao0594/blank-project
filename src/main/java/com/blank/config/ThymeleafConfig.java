package com.blank.config;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ThymeleafConfig {

	private final Logger LOGGER = LoggerFactory.getLogger(ThymeleafConfig.class);

	@Bean
	@Description("Thymeleaf template")
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templates/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("HTML");
		emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
		emailTemplateResolver.setCacheable(false);
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public SpringSecurityDialect springSecurity() {
		return new SpringSecurityDialect();
	}

	// @Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	// @Bean
	// public ShoppayDialect shopDialect() {
	// return new ShoppayDialect();
	// }

}
