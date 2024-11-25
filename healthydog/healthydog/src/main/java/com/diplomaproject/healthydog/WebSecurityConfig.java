package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig   {

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /*
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/users").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login.usernameParameter("email")
                                .defaultSuccessUrl("/home_page")
                                .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }*/


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider())
                .authorizeRequests()
                .requestMatchers("/add_dog", "/add_dog-form").authenticated()  // Protect add dog routes
                .anyRequest().permitAll()  // Allow other pages to be publicly accessible
                .and()
                .formLogin()
                .usernameParameter("email") // Use email as the username
                .defaultSuccessUrl("/home_page", true) // Redirect to home page after successful login
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll(); // Redirect to home page after logout

        return http.build();
    }

}