package com.trade_platform.Security;

import com.trade_platform.Security.ApiKey.ApiKeyAuthenticationFilter;
import com.trade_platform.Security.ApiKey.ApiKeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    @Autowired
    public SecurityConfiguration(
        ApiKeyAuthenticationProvider apiKeyAuthenticationProvider
    ) {
        this.apiKeyAuthenticationProvider = apiKeyAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(registry -> registry.anyRequest().permitAll())
            .addFilterBefore(new ApiKeyAuthenticationFilter(this.authenticationManager()), AnonymousAuthenticationFilter.class)
            .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
            Collections.singletonList(this.apiKeyAuthenticationProvider)
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}