package com.telran.java4h5hspringsecurity1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //этот бин создадут раньше всех остальных
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    /**
     * Bean Creation
     * 1. @Component, @Service, @Controller, @RestController - Annotation Config
     * 2. @Bean -> сами решаем как создать bean
     */


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //CSRF - отключить проверку целостности HTML страниц
        http.csrf().disable()
                //Управление переходит ко мне
                //Spring Security - не помогай мне проверять session
                //Session (сессия) - все запросы от login до logout (все действия от входа до выхода)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //все запросы должны проходить проверку "на токен"
                .authorizeRequests()

                //вот список куда кому можно
                .antMatchers("/endpoint-100").authenticated() //authenticated - для тех, кто пройдет фильтр И (!) получит ключ
                .antMatchers("/endpoint-1").permitAll() //permitAll - без проверки фильтром
                .antMatchers("/endpoint-2").permitAll()
        ;

        //There are 12-14 Spring Security default filters
        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
