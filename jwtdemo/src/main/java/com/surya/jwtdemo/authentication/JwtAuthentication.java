package com.surya.jwtdemo.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtAuthentication implements Authentication {
    private List<GrantedAuthority> authorities;
    private String credentials;
     private boolean authenticaion;
//     private  String principal;
     private String name;

    public JwtAuthentication(List<GrantedAuthority> authorities, String credentials, boolean authenticaion, String name) {
        this.authorities = authorities;
        this.credentials = credentials;
        this.authenticaion = authenticaion;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticaion;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
