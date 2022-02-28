package com.smartauto.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // for preAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override  
    public void configure(HttpSecurity http) throws Exception {  
        http 
            .csrf().disable()
            .authorizeRequests() 
            .anyRequest().authenticated()  
            .and()  
            .formLogin()
            .and()
            .logout().logoutSuccessUrl("/");
    }  

    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        auth.inMemoryAuthentication()  
            .withUser("user1").password("{noop}pass").roles("BUYER")
            .and()
            .withUser("user2").password("{noop}pass").roles("BUYER")
            .and()
            .withUser("user3").password("{noop}pass").roles("BUYER")
            .and()
            .withUser("admin").password("{noop}admin").roles("ADMIN");
    } 

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
