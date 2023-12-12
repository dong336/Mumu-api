package io.backofficeApi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

public class SpaRedirectFiler extends OncePerRequestFilter {

    private final String REGEX = "(?!/actuator|/api|/_nuxt|/static|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
    private Pattern pattern = Pattern.compile(REGEX);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Forwards all routes except '/index.html', '/200.html', '/favicon.ico', '/sw.js' '/api/', '/api/**'
        try{
            if (pattern.matcher(request.getRequestURI()).matches() && !request.getRequestURI().equals("/")) {
                //System.out.println("URL 리다이렉트"+ request.getRequestURI());
                RequestDispatcher rd = request.getRequestDispatcher("/");
                rd.forward(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
