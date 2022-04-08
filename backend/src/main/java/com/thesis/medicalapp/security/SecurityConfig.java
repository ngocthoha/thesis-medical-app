package com.thesis.medicalapp.security;

import com.thesis.medicalapp.filter.CustomAuthenticationFilter;
import com.thesis.medicalapp.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure");
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll()
                .antMatchers(POST, "/api/auth/signup/**").permitAll()
                .antMatchers(GET, "/api/users/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/profiles/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/schedules/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/specialties/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/upload/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/files/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/appointments/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/doctor/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/records/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/doctors/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/rooms/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
