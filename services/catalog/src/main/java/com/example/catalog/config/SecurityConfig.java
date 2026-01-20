package com.example.catalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/actuator/health/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/catalog/**").hasAnyRole("CATALOG_READ", "CATALOG_ADMIN")
            .requestMatchers("/api/v1/catalog/**").hasRole("CATALOG_ADMIN")
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(
      PasswordEncoder encoder,
      @Value("${catalog.security.username:catalog-admin}") String username,
      @Value("${catalog.security.password:ChangeMe!}") String password) {
    UserDetails user = User.builder()
        .username(username)
        .password(encoder.encode(password))
        .roles("CATALOG_ADMIN")
        .build();

    UserDetails reader = User.builder()
        .username("catalog-reader")
        .password(encoder.encode("ChangeMeReader!"))
        .roles("CATALOG_READ")
        .build();

    return new InMemoryUserDetailsManager(user, reader);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
