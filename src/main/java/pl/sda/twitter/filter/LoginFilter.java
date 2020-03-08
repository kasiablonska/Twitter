package pl.sda.twitter.filter;

import pl.sda.twitter.constans.SessionValues;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

@WebFilter(urlPatterns = "/add-article.jsp")
public class LoginFilter implements Filter {
    private final String userValue = SessionValues.USER.getValue();
    private final String messageValue = SessionValues.MESSAGES.getValue();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Object user = httpRequest.getSession().getAttribute(userValue);
        if (user == null) {
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final HttpSession session=httpRequest.getSession();
            httpRequest.getSession().setAttribute(messageValue, Collections.singletonList("Zalouj się ponownie aby kontynułować"));
            httpResponse.sendRedirect("login.jsp");
        }
        chain.doFilter(request, response);
    }
}
