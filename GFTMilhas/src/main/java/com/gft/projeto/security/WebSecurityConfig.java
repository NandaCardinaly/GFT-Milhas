package com.gft.projeto.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	final UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/", "/evento/listar", "/grupo", "/participante", "/atividade", "/ranking/listar").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.successHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				response.sendRedirect("/");
				
			}
		})
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
