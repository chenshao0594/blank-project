/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/access").setViewName("access");
		registry.addViewController("/test").setViewName("test");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true).favorParameter(true);
	}

	@Bean
	public FilterRegistrationBean hiddenFilterRegistrationBean() {
		return new FilterRegistrationBean(new HiddenHttpMethodFilter());
	}

	// @Bean
	// public FlowHandlerMapping flowHandlerMapping() {
	// FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
	// handlerMapping.setOrder(-1);
	// handlerMapping.setFlowRegistry(this.webFlowConfig.flowRegistry());
	// return handlerMapping;
	// }

	// @Bean
	// public FlowHandlerAdapter flowHandlerAdapter() {
	// FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
	// handlerAdapter.setFlowExecutor(this.webFlowConfig.flowExecutor());
	// handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
	// return handlerAdapter;
	// }
	//
	// @Bean
	// public AjaxThymeleafViewResolver ajaxThymeleafViewResolver() {
	// AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
	// viewResolver.setViewClass(FlowAjaxThymeleafView.class);
	// viewResolver.setTemplateEngine(springTemplateEngine);
	// return viewResolver;
	// }
	/**
	 * 该方法用于注册拦截器 可注册多个拦截器，多个拦截器组成一个拦截器链
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 添加路径
		// excludePathPatterns 排除路径
		registry.addInterceptor(new PortalInterceptor()).addPathPatterns("/*.*");

	}
}
