package com.mohdasha.security.auth;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class AdditionalAuthDetails extends WebAuthenticationDetails {

    private String securityPin;
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public AdditionalAuthDetails(HttpServletRequest request) {
        super(request);

        String securityPin = request.getParameter("securityPin");
        if(securityPin != null)
            this.securityPin = securityPin;
    }

    public String getSecurityPin() {
        return securityPin;
    }
}
