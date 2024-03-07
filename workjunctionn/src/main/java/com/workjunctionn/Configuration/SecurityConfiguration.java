package com.workjunctionn.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.workjunctionn.CustomService.CustomJobSeekerDetailsService;
import com.workjunctionn.CustomService.CustomRecruiterDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomJobSeekerDetailsService customJobSeekerDetailsService;

    @Autowired
    private CustomRecruiterDetailsService customRecruiterDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customJobSeekerDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(customRecruiterDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/auth/jobseeker/signup", "/api/auth/jobseeker/signin").permitAll()
//                .antMatchers("/api/recruiter/**").permitAll()
//                .antMatchers("/api/job/**").permitAll() // Permit access to all endpoints under /api/job/
//                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()
//            .and() 
//            .httpBasic(); 
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/**").permitAll() // Permit access to all endpoints for all users
//                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                .antMatchers("/**").permitAll() // Allow access to all APIs without authentication
//                .anyRequest().authenticated()
//            .and() 
//            .httpBasic(); 
//    }

}
