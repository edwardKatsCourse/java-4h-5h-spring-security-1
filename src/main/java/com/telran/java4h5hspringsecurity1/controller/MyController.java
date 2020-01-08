package com.telran.java4h5hspringsecurity1.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
public class MyController {

    @GetMapping("/my-endpoint")
    public String success() {
        System.out.println("controller");
        return "Success";
    }

    @GetMapping("/endpoint-1")
    public String endpoint1() {
        System.out.println("endpoint-1 controller");
        return "success - endpoint-1";
    }

    @GetMapping("/endpoint-2")
    public String endpoint2() {
        System.out.println("endpoint-2 controller");
        return "success - endpoint-2";

    }

    @GetMapping("/endpoint-100")
    public String endpoint100() {
        System.out.println("endpoint-100 controller");
        return "success - endpoint-100";
    }
}


//@Component
class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,

                         FilterChain filterChain) throws IOException, ServletException {
        //ServletRequest -> HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //Если все ОК - вызываем этот метод

        System.out.println("Filter");

        //Authorization
        //value -> database -> user
        if (req.getHeader("java-4h-5h") != null) {
            System.out.println("Header -> " + req.getHeader("java-4h-5h"));
            filterChain.doFilter(req, resp);
        } else {
            resp.setStatus(400);
            resp.getWriter().println("Authentication header is not valid");
        }
    }
    //doGet
    //doPost
    //doDelete
}

class A {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
    }
}