package mk.ukim.finki.wp.lab.web.filter;

import mk.ukim.finki.wp.lab.model.Course;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class AppFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // za da zememe info od sesija castirame
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Long course = (Long) request.getSession().getAttribute("chosenCourse");
        String path = request.getServletPath(); // patekata do koja korisnikot probuva da pristapi

        if (course == null && !"/AddStudent".equals(path) && !path.contains("/courses") && !"/main.css".equals(path) && !"/login".equals(path)) {
            response.sendRedirect("/courses?error=CourseNotSelectedException");
        } else {
            filterChain.doFilter(servletRequest, servletResponse); // ja prodolzuvame verigata
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
