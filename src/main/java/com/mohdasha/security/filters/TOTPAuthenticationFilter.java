package com.mohdasha.security.filters;

import com.mohdasha.security.model.Authorities;
import com.mohdasha.security.service.TOTPQueryService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

public class TOTPAuthenticationFilter extends GenericFilterBean {

    @Autowired private TOTPQueryService totpQueryService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String totpCode = getTOTPCode(request);

        if(totpCode == null || !requiresTotpAuthentication(authentication)) {
            doFilter(request, response, chain);
            return ;
        }


    }

    private String getTOTPCode(ServletRequest request) {
        return request.getParameter("totp_code");
    }

    private boolean requiresTotpAuthentication(Authentication authentication) {
        if(authentication == null)
            return false;

        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        boolean hasTotpAuthority = authorities.contains(Authorities.TOTP_AUTH_AUTHORITY);

        return hasTotpAuthority && authentication.isAuthenticated();
    }
}
