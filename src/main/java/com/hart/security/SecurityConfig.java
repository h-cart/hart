
package com.hart.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hart.security.handler.LoginFailureHandler;
import com.hart.security.handler.LoginSuccessHandler;
import com.hart.service.ClubUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final ClubUserDetailsService memberService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService);
		super.configure(auth);
	}

	@Bean
	public LoginSuccessHandler successHandler() {
		return new LoginSuccessHandler(passwordEncoder());
	}//

	@Bean
	LoginFailureHandler getFailureHandler() {
		return new LoginFailureHandler();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..

	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// /samle/all 모든 사용자 가능
		// /sample/member USER 롤 사용자만
		http.authorizeRequests().antMatchers("/cart/**").authenticated().antMatchers("/").permitAll()
				.antMatchers("/order/**").authenticated().antMatchers("/mypage/**").authenticated()
				.antMatchers("/member/login/**").permitAll().antMatchers("/member/login_form").permitAll()
				.antMatchers("/member/**").hasRole("USER").antMatchers("/admin/**").hasRole("ADMIN");
//				.antMatchers("/admin").hasRole("ADMIN");
		// 인가 인증 문제시 로그인 화면

		http.formLogin().loginPage("/member/login").loginProcessingUrl("/member/login_form").defaultSuccessUrl("/admin")
				.failureHandler(getFailureHandler()).successHandler(successHandler()).permitAll();

		// crsf 비활성화
		http.csrf();// .disable();
		http.cors();
		// 로그 아웃 세팅
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/member/login");

		// 구글 oauth 인증 추가
		/* http.oauth2Login(); */
		http.oauth2Login().loginPage("/login").successHandler(successHandler());
		http.rememberMe() // 7day
				.tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(userDetailsService());

	}

	@Bean
	public HttpFirewall defaultHttpFireWall() {
		return new DefaultHttpFirewall();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(defaultHttpFireWall());
	}// end configure http

	@Bean
	public RequestCache requestCache() {
		return new HttpSessionRequestCache();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("Origin URL 등록"));
		configuration.setAllowedMethods(Arrays.asList("사용할 CRUD 메소드 등록"));
		configuration.setAllowedHeaders(Arrays.asList("사용할 Header 등록"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}// end class
