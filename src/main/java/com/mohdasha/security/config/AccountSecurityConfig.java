package com.mohdasha.security.config;

import com.mohdasha.security.auth.AdditionalAuthDetailsSource;
import com.mohdasha.security.model.Authorities;
import com.mohdasha.security.service.NoSQLUserDetailsServiceImpl;
import com.mohdasha.security.userdetails.AdditionalAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AccountSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private NoSQLUserDetailsServiceImpl userDetailsService;
    @Autowired private AdditionalAuthenticationProvider additionalAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/account/login").permitAll()
                    .antMatchers("/account/register").permitAll()
                    .antMatchers("/totp/login","/totp/error").hasAuthority(Authorities.TOTP_AUTH_AUTHORITY)
                    .anyRequest().hasRole(Authorities.ROLE_USER)
                .and()
                    .formLogin()
                    .loginPage("/account/login").successForwardUrl("/account/home")
                    .authenticationDetailsSource(new AdditionalAuthDetailsSource());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(additionalAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
