package com.estudy.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.estudy.jwt.JwtAuthenticationFilter;
import com.estudy.service.impl.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserService userService;

        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
                return new JwtAuthenticationFilter();
        }

        @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
                // Get AuthenticationManager bean
                return super.authenticationManagerBean();
        }

        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.cors().and().csrf().disable();

                // Set session management to stateless
                http = http
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and();

                // Set unauthorized requests exception handler
                http = http
                                .exceptionHandling()
                                .authenticationEntryPoint(
                                                (request, response, ex) -> {
                                                        response.sendError(
                                                                        HttpServletResponse.SC_UNAUTHORIZED,
                                                                        ex.getMessage());
                                                })
                                .and();

                // Set permissions on endpoints
                http.authorizeRequests()
                                // public endpoints
                                .antMatchers(HttpMethod.GET, "/api/v1/category/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/v1/comment/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/v1/user/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/v1/course/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/v1/course/search").permitAll()
                                .antMatchers("/api/v1/auth/**", "/")
                                .permitAll()

                                // private endpoints
                                // .antMatchers("/api/v1/user/**").hasAuthority("ADMIN")
                                // .antMatchers("/api/v1/category/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated();

                http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
                web.ignoring().antMatchers("/v2/api-docs/**");
                web.ignoring().antMatchers("/swagger.json");
                web.ignoring().antMatchers("/swagger-ui.html");
                web.ignoring().antMatchers("/swagger-resources/**");
                web.ignoring().antMatchers("/webjars/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userService)
                                .passwordEncoder(passwordEncoder);
        }
}
