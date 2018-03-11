package com.devopsbuddy.config;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.devopsbuddy.service.UserSecuirtyService;
import com.devopsbuddy.web.ForgotMyPasswordContorller;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	private final static String SALT = "ghfdgfuedeu";

	@Bean
	public BCryptPasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	@Autowired
	private UserSecuirtyService userSecuirtyService;

	private static final String[] PUBLIC_MATCHERS = {

			"/webjars/**", "/css/**", "/js/**", "/images/**", "/", "/about/**", "/contact/**", "/error/**/*",
			"/h2-console/**", "/forgotmypassword/**", ForgotMyPasswordContorller.CHANGE_PASSWORD_PATH

	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> activepprfile = Arrays.asList(env.getActiveProfiles());
		if (activepprfile.contains("dev")) {

			http.csrf().disable();
			http.headers().frameOptions().disable();
		}
		http

				.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/payload", true).usernameParameter("username")
				.passwordParameter("password").failureUrl("/login?error").permitAll().and().logout().permitAll();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticatonManager) throws Exception {

		// authenticatonManager.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		authenticatonManager.userDetailsService(userSecuirtyService).passwordEncoder(passwordencoder());

	}

}
