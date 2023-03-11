
package com.hart.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

/**
 * Description : 시큐리티 설정<br>
 * Date : 2023. 2. 5.<br>
 * History :<br>
 * - 작성자 : 이승규, 날짜 : 2023. 2. 5., 설명 : 시큐리티 설정 <br>
 * @author 이승규
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		log.info("실행");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchyImpl;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { /// samle/all 모든 사용자 가능 // /sample/member USER 롤 사용자만

		http.authorizeRequests().antMatchers("/*").permitAll();
		/*
		 * http.rememberMe().rememberMeParameter("rememberMe").tokenValiditySeconds(3600
		 * * 24 * 365) .userDetailsService(userDetailsService);
		 */
	}


}
// end configure http }// end class
