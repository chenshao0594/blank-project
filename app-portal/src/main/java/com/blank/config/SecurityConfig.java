package com.blank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] UNSECURED_RESOURCE_LIST = new String[] { "/resources/**", "/assets/**", "/css/**",
			"/webjars/**", "/images/**", "/dandelion/**", "/js/**" };

	private static final String[] UNAUTHORIZED_RESOURCE_LIST = new String[] { "/test/**", "/", "/unauthorized*",
			"/error*", "/accessDenied" };

	// private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Autowired
	private UserDetailsService userDetailsService;

	// private final RememberMeServices rememberMeServices;

	// private final CorsFilter corsFilter;

	@Value("${rememberMeToken}")
	private String rememberMeToken;

	// public SecurityConfigBK(AuthenticationManagerBuilder
	// authenticationManagerBuilder,
	// UserDetailsService userDetailsService, RememberMeServices rememberMeServices,
	// CorsFilter corsFilter) {
	//
	// this.authenticationManagerBuilder = authenticationManagerBuilder;
	// this.userDetailsService = userDetailsService;
	// this.rememberMeServices = rememberMeServices;
	// this.corsFilter = corsFilter;
	// }

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(UNSECURED_RESOURCE_LIST);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and().authorizeRequests().antMatchers(UNAUTHORIZED_RESOURCE_LIST)
				.permitAll().antMatchers("/manage/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().headers().cacheControl().and().frameOptions().deny().and()
				.exceptionHandling().accessDeniedPage("/access?error").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").and()
				.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}
