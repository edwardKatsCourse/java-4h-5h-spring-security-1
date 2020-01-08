package com.telran.java4h5hspringsecurity1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityFilter extends OncePerRequestFilter {

    List<String> users = Arrays.asList("johnd");

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,

                                    FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");

        if (token != null) {
            System.out.println("SecurityFilter -> " + token);

            //1. Create Key from token
            //2. Set key for Thread memory

            //checking token existence in "database"

            //USER_SESSION
            if (users.contains(token)) {
                //Key

                Authentication key = new UsernamePasswordAuthenticationToken(
                        token, //user or username or user entity
                        null, //always null, since Spring Security does not manage passwords
                        new ArrayList<>() //roles
                );

                //Thread context memory
                //SecurityContextHolder - создается под каждый запрос
                //SecurityContextHolder - виден только в рамках запроса

                //Передаем ключ внутрь Thread Context
                SecurityContextHolder.getContext().setAuthentication(key);
            }
        }

        //Передаем эстафету вне зависимости от осхода работы фильтра
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}


//class A extends Thread {
//
//    private String SecurityContextHolder;
//}