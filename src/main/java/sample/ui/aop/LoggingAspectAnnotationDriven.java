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
package sample.ui.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Class used to house all aspect-related method.
 *
 * @author arnaldopiccinelli
 */
@Aspect
@EnableAspectJAutoProxy
@Component
@Configuration
public class LoggingAspectAnnotationDriven {

	@Before("execution(* sample.ui.web.GeneralController.handleException(..))")
	public void beforeExceptionHandler(JoinPoint jp) {
		// log.info("(beforeExceptionHandler) Before executing '" +
		// jp.getSignature().toLongString() + "'");
	}

	@After("execution(* sample.ui.web.GeneralController.handleException(..))")
	public void afterExceptionHandler(JoinPoint jp) {
		// log.info("(afterExceptionHandler) Before executing '" +
		// jp.getSignature().toLongString() + "'");
	}

	@Before("execution(* sample.ui.config.MultiHttpSecurityConfig.*.*(..))")
	public void beforeMultiHttpSecurityConfigSubClasses(JoinPoint jp) {
		// log.info("(beforeMultiHttpSecurityConfigSubClasses) Before executing '" +
		// jp.getSignature().toLongString()
		// + "'");
	}

	@Before("execution(* sample.ui.config.MultiHttpSecurityConfig.*(..))")
	public void beforeMultiHttpSecurityConfig(JoinPoint jp) {
		// log.info("(beforeMultiHttpSecurityConfig) Before executing '" +
		// jp.getSignature().toLongString() + "'");
	}

	@Before("execution(* sample.ui.SampleApplication.*(..))")
	public void beforeSampleWebUiApplication(JoinPoint jp) {
		// log.info("(beforeSampleWebUiApplication) Before executing '" +
		// jp.getSignature().toLongString() + "'");
	}

	@After("execution(* sample.ui.SampleApplication.*(..))")
	public void afterSampleWebUiApplication(JoinPoint jp) {
		// log.info("(afterSampleWebUiApplication) After executing '" +
		// jp.getSignature().toLongString() + "'");
	}

}
