package com.surya.jwtdemo.config;

import com.surya.jwtdemo.filters.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests()
                 .mvcMatchers("/authenticate")
                 .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(getJwtTokenFilter(), BasicAuthenticationFilter.class);

    }

    @Bean
    public UserDetailsService getUserDetailsService()
    {
        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
        User.builder().username("surya").password("surya");
        inMemoryUserDetailsManager.createUser(User.builder().username("sueya").password("surya").authorities("read").build());
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JwtTokenFilter getJwtTokenFilter()
    {
        return new JwtTokenFilter();
    }
}
