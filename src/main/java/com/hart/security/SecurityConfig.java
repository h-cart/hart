
package com.hart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hart.security.handler.LoginSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin@hart.shop").password("{noop}qwer1234").roles("ADMIN");
	}

	// ClubLoginSuccessHandler 등록
	@Bean
	public LoginSuccessHandler successHandler() {
		return new LoginSuccessHandler(passwordEncoder());
	}// end CLu..

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..

	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		log.info("실행");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //사용자 계정 세팅 user1//패스워드 1111
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("$2a$10$qbTVRGiC8RePIsMz4z/QP.LjBmLOMGXBCkmW2comzfNaoeidd5/aa")
//                .roles("USER");
//    }//configure AM

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// /samle/all 모든 사용자 가능
		// /sample/member USER 롤 사용자만
		http.authorizeRequests().antMatchers("/cart/**").authenticated().antMatchers("/").permitAll()
				.antMatchers("/member").hasRole("USER").antMatchers("/admin").hasRole("ADMIN");
		// 인가 인증 문제시 로그인 화면
		http.formLogin().loginPage("/member/login").defaultSuccessUrl("/");
		// crsf 비활성화
		http.csrf();// .disable();
		// 로그 아웃 세팅
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/member/login");

		// 구글 oauth 인증 추가
		/* http.oauth2Login(); */
		http.oauth2Login().loginPage("/login").successHandler(successHandler());
		http.rememberMe() // 7day
				.tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(userDetailsService());

	}// end configure http
}// end class
