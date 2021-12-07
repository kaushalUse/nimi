package com.rootcode.test.practicalTest.config;

import com.rootcode.test.practicalTest.entity.LedgerUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("view-add-transactions");
        }
        if (roles.contains("ROLE_OPERATIONS")) {
            response.sendRedirect("view-transactions");
        }

        Long entityId = extractEntityId(authentication);
        if (entityId instanceof Long) {
            request.getSession().setAttribute("CUSTOMER_ID", entityId);
        }
    }

    private Long extractEntityId(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        Object entityId;
        if (principal instanceof LedgerUser) {
            LedgerUser user = (LedgerUser) principal;
            entityId = user.getId();
        } else {
            entityId = authentication.getDetails();
        }
        if (entityId instanceof Long) {
            return (Long) entityId;
        }
        return null;
    }
}