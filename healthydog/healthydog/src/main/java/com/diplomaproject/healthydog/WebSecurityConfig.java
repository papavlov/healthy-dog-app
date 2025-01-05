package com.diplomaproject.healthydog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig {

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

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/uploads/**").permitAll() // Permits static and uploaded files
                .requestMatchers("/dogs/add_dog", "/dogs/add_dog-form", "/dogs").authenticated()
                .requestMatchers("/dogs/{dogId}/vaccines/add", "/dogs/{dogId}/vaccines/view").authenticated()
                .requestMatchers("/api/dogfood/recommendations/{dogId}", "/api/dogsupplements/recommendations/{dogId}", "/dogs/{dogId}/collars").authenticated()
                .requestMatchers("/dogs/{dogId}/walks/add", "/dogs/{dogId}/walks/history", "/dogs/{dogId}/walks/history/filter").authenticated()
                .requestMatchers("/forgot-password", "/reset-password").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/login")  // Handles POST to /login
                        .defaultSuccessUrl("/home_page", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/logout-success")
                        .permitAll()
                )
                .csrf();
                //.ignoringRequestMatchers("/forgot-password", "/reset-password"); // Disables CSRF for specific endpoints

        return http.build();
    }

    // New configuration class to handle static resources
    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            // This maps the 'uploads' folder in the root directory to be accessible via /uploads/**
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:uploads/");  // Specifies that 'uploads' folder is under the project root
        }
    }
}
