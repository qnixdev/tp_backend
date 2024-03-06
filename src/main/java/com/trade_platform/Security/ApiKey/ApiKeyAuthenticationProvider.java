package com.trade_platform.Security.ApiKey;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = authentication.getPrincipal().toString();

        if (ObjectUtils.isEmpty(apiKey)) {
            throw new InsufficientAuthenticationException("API key can't by empty!");
        } else {
            //TODO: search api key
            if ("ValidApiKey".equals(apiKey)) {
                return new ApiKeyAuthenticationToken(apiKey, true);
            }

            throw new BadCredentialsException("API key is invalid!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}