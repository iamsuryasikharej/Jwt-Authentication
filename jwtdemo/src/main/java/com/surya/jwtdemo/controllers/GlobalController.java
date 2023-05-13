package com.surya.jwtdemo.controllers;

import com.surya.jwtdemo.domain.AuthorizationRequest;
import com.surya.jwtdemo.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GlobalController {
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/authenticate")
    public String authenticate(@RequestHeader Map<String,String> header)
    {
        System.out.println("sysys");
        String uname=header.get("uname");
        String upass=header.get("upass");
        System.out.println(uname+"++"+upass);
        Authentication a=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(uname,upass));
        System.out.println(a.isAuthenticated());
        if(a.isAuthenticated())
        {
            HashMap<String,Object> claims=new HashMap<>();
            return new JwtUtils().createToken(uname, claims);
        }
        else
            throw new BadCredentialsException("something is wrong");



    }
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
