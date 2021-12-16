package org.jjh.murmur.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //스프링 시큐리티 활성화 어노테이션
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Bean public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); 
	   }
	   
	   @Autowired
	   private DataSource dataSource;

	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http
	        .csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/", "/member/joinForm", "/css/**", "/api/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/member/login")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();

	   }

	   @Autowired
	   public void configureGlobal(AuthenticationManagerBuilder auth)
	           throws Exception {
	       auth.jdbcAuthentication()
	               .dataSource(dataSource)
	               .usersByUsernameQuery("select username, password, enabled "
	                       + "from user "
	                       + "where username = ?")
	               .authoritiesByUsernameQuery("select u.username, r.authName "
	                       + "from user_role ur inner join user u on ur.user_id = u.id "
	                       + "inner join role r on ur.role_id = r.id "
	                       + "where u.username = ?");
	   }

   

    
}