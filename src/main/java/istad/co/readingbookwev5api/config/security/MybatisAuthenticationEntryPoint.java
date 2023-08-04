package istad.co.readingbookwev5api.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class MybatisAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException){
            throw new BadCredentialsException(authException.getMessage());

    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("CSTAD Reading");
        super.afterPropertiesSet();
    }
}
