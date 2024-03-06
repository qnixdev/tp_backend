package com.trade_platform.Security.ApiKey;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    private String apiKey;

    public ApiKeyAuthenticationToken(String apiKey, boolean authenticated) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.apiKey = apiKey;
        this.setAuthenticated(authenticated);
    }

    public ApiKeyAuthenticationToken(String apiKey) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.apiKey = apiKey;
        this.setAuthenticated(false);
    }

    public ApiKeyAuthenticationToken() {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}