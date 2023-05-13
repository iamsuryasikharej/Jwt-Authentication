package com.surya.jwtdemo.filters;

import com.surya.jwtdemo.authentication.JwtAuthentication;
import com.surya.jwtdemo.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class JwtTokenFilter implements Filter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwtToken = httpServletRequest.getHeader("token");
        System.out.println("{{"+jwtToken+"}}");
        System.out.println(jwtToken==null);
        if (jwtToken!=null) {
            JwtUtils jwtUtils = new JwtUtils();
            String uname = jwtUtils.extractUsername(jwtToken);
            UserDetails ud = userDetailsService.loadUserByUsername(uname);
            if (jwtUtils.validateToken(jwtToken, ud)) {
                JwtAuthentication jwtAuthentication = new JwtAuthentication(new ArrayList<>(), ud.getPassword(), true, ud.getUsername());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(jwtAuthentication);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                throw new BadCredentialsException("Invalid Jwt token");
            }


        }
        else
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
