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
   
   // static 하위 폴더 (css, js, img)는 무조건 접근이 가능해야하기 때문에 인증을 무시
   @Override
   public void configure(WebSecurity web) { 
     web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
   }

   // 접급 권한 설정
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
        .csrf().disable()
        
        .authorizeRequests()
            .antMatchers("/", "/member/joinForm", "/member/login", "/css/**", "/api/**").permitAll()	// 누구나 접근 허용
            .antMatchers("/").hasRole("USER")	// USER, ADMIN만 접근 가능
            .antMatchers("/admin").hasRole("ADMIN")	// ADMIN만 접근 가능
            .anyRequest().authenticated()	// 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
            
        .and()
        
        .formLogin()
	    	.loginPage("/member/login")
	        .permitAll()
	        .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
        
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
               .authoritiesByUsernameQuery("select u.username, r.name "
                       + "from user_role ur inner join user u on ur.user_id = u.id "
                       + "inner join role r on ur.role_id = r.id "
                       + "where u.username = ?");
   }

   

    
}
