package sample.ui.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

//@Configuration
//@EnableWebSecurity
//@Order(1)
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] UNSECURED_RESOURCE_LIST = new String[] { "/resources/**", "/assets/**", "/css/**",
			"/webjars/**", "/images/**", "/dandelion/**", "/js/**" };

	private static final String[] UNAUTHORIZED_RESOURCE_LIST = new String[] { "/test.html", "/", "/unauthorized*",
			"/error*", "/users*", "/accessDenied" };

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private final UserDetailsService userDetailsService;

	private final RememberMeServices rememberMeServices;

	private final CorsFilter corsFilter;
	
	@Value("${rememberMeToken}")
	private String rememberMeToken;

	public SecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder,
			UserDetailsService userDetailsService,
			RememberMeServices rememberMeServices, CorsFilter corsFilter) {

		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDetailsService = userDetailsService;
		this.rememberMeServices = rememberMeServices;
		this.corsFilter = corsFilter;
	}

	@PostConstruct
	public void init() {
		try {
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			throw new BeanInitializationException("Security configuration failed", e);
		}
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
		// @formatter:off
		http.headers().frameOptions().sameOrigin().and().authorizeRequests().antMatchers(UNAUTHORIZED_RESOURCE_LIST)
		.permitAll().antMatchers("/git", "/manage", "/manage/**").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().and().headers().cacheControl().and()
		.frameOptions().deny().and().exceptionHandling().accessDeniedPage("/access?error").and()
		.rememberMe().useSecureCookie(true).tokenValiditySeconds(60 * 60 * 24 * 10) // 10 days
		.rememberMeServices(rememberMeServices).key(rememberMeToken).and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").and()
		.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
		// @formatter:on
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}
