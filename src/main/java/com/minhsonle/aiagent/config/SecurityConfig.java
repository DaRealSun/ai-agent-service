package com.minhsonle.aiagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        PathPatternRequestMatcher.Builder mvc = PathPatternRequestMatcher.withDefaults();

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.matcher("/actuator/health")).permitAll()
                        .requestMatchers(mvc.matcher("/api/v1/auth/**")).permitAll()
                        .requestMatchers(mvc.matcher("/error")).permitAll()      // ← ADD THIS LINE
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
// Using PathPatternRequestMatcher explicitly instead of bare strings.
// Bare requestMatchers(String) defaults to MvcRequestMatcher in Spring Security 6,
// which consults MVC handler mappings with side effects (spurious Allow headers,
// wildcard match quirks). PathPattern is pure pattern matching, predictable.