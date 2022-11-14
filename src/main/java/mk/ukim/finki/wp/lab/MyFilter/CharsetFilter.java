package mk.ukim.finki.wp.lab.MyFilter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class CharsetFilter implements Filter { // ne e idealno

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
