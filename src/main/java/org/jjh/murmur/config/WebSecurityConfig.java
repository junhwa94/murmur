package org.jjh.murmur.config;



import org.jjh.murmur.service.MemberService;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //스프링 시큐리티 활성화 어노테이션
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MemberService memberService;

   @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
        		
                .loginPage("/member/login")                    // 로그인 페이지 url
                .defaultSuccessUrl("/")                         // 로그인 성공 시 이동 할 url
                .usernameParameter("email")                     // 로그인 시 사용 할 파라미터 이름 email
                .failureUrl("/member/login/error")             // 로그인 실패 시 이동 할 url
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))     // 로그아웃 url
                .logoutSuccessUrl("/")                                                         // 로그아웃 성공 시 이동 할 url
        ;

        http
    		.csrf().disable()
    		.authorizeRequests()    // 시큐리티 처리를 HttpSecurity 서비스 이용
                .mvcMatchers("/", "/member/**", "/css/**", "/api/**", "/images/**").permitAll()      // permitAll()을 통해 모든 사용자가 인증(로그인)없이 해당 경로에 접근가능
                .mvcMatchers("/admin/**").hasRole("ADMIN")      // 어드민 계정만 접근 가능
                .anyRequest().authenticated()       // 모든 사용자가 접근 가능 , 어드민 계정만 접근 가능한 경로를 제외한 마저지 경로들은 모두 인증 요구
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())     // 인증되지 않은 사용자가 리소스에 접그했을 때 수행되는 핸들러
        ;

    }
   
   @Bean
   public PasswordEncoder passwordEncoder() {

       return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception{

       auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());

   }

   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");     // static 디렉토리 하위 파일으 인증 무시하도록 설정
   }
   
   
   

    
}
