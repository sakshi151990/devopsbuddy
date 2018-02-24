package com.devopsbuddy.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private static final String[] PUBLIC_MATCHERS= {
			
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**/*"
			
			
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{  
		System.out.println("inside configure");
		http.csrf().disable();
	   http
	   
	    .authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/contact",true).usernameParameter("username").passwordParameter("password")
        .failureUrl("/login?error").permitAll()
        .and()
        .logout().permitAll();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticatonManager) throws Exception
	{ System.out.println("inside global");
		authenticatonManager
		.inMemoryAuthentication()
		.withUser("user")
		.password("password")
		.roles("USER");
		
	}
	
}
