package com.blank.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
	public static final String SYSTEM_ACCOUNT = "system";

	@Override
	public Optional<String> getCurrentAuditor() {
		String userName = SecurityUtils.getCurrentUserLogin();
		String result = userName != null ? userName : SYSTEM_ACCOUNT;
		return Optional.of(result);
	}
}
