package sample.ui.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
	public static final String SYSTEM_ACCOUNT = "system";


    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? userName : SYSTEM_ACCOUNT;
    }
}

