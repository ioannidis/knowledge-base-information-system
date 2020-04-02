package eu.ioannidis.vks.authenticationservice.utils;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VksAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof DisabledException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
            return;
        }

        if (authException instanceof BadCredentialsException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
            return;
        }

        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, authException.getMessage());
    }
}
