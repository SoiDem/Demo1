

package com.pj.media.config;

import com.learning.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Các User trong Database
        //auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance()); 
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());  
 
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        //cau hinh role quyen
        //http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");  
        http.authorizeRequests().antMatchers("/chart").access("hasRole('ROLE_CHART')");
        http.authorizeRequests().antMatchers("/person/list").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/detail").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/save").access("hasRole('ROLE_PERSON')");
        
        http.authorizeRequests().antMatchers("/autocomplete/").access("hasRole('ROLE_AUTOCOMPLETE')");
        http.authorizeRequests().antMatchers("/autocomplete/getTags").access("hasRole('ROLE_AUTOCOMPLETE')");
        http.authorizeRequests().antMatchers("/autocomplete/postData").access("hasRole('ROLE_AUTOCOMPLETE')");

        

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//

                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

    }
    
}


