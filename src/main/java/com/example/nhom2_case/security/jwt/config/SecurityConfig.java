package com.example.nhom2_case.security.jwt.config;



import com.example.nhom2_case.security.jwt.CustomAccessDeniedHandler;
import com.example.nhom2_case.security.jwt.JwtAuthenticationTokenFilter;
import com.example.nhom2_case.security.jwt.RestAuthenticationEntryPoint;
import com.example.nhom2_case.security.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public SecurityConfig(AccountServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public SecurityConfig(boolean disableDefaults, AccountServiceImpl userServiceImpl) {
        super(disableDefaults);
        this.userServiceImpl = userServiceImpl;
    }

    public SecurityConfig() {
    }

    @Autowired
    private AccountServiceImpl userServiceImpl;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    protected void configure (HttpSecurity http) throws Exception {
        // Disable crsf cho đường dẫn /api/**
        http.csrf().ignoringRequestMatchers();
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/client/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }

}
