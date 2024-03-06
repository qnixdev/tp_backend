package com.trade_platform.Security.LoginForm;

import com.trade_platform.Repository.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Collections;

@Component
public class CustomerLoginFormAuthenticationProvider implements AuthenticationProvider {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerLoginFormAuthenticationProvider(
        CustomerRepository customerRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName().toLowerCase();
        String password = authentication.getCredentials().toString();

        var existCustomer = this.customerRepository.getCustomerByEmailAndPassword(
            email,
            this.passwordEncoder.encode(password)
        );

        if (null != existCustomer) {
            return new UsernamePasswordAuthenticationToken(
                email,
                password,
                Collections.emptyList()
            );
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}