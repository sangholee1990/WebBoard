package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import java.io.IOException;


// 서블릿 컨테이너로부터 필터 적용
@WebFilter(filterName = "BoardFilter", urlPatterns = {"*"})
public class BoardFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(String.format("[START] 서블릿 필터 : %s", "init"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        System.out.println(String.format("[PROC] 서블릿 필터 : %s", "doFilter"));
    }

    @Override
    public void destroy() {
        System.out.println(String.format("[END] 서블릿 필터 : %s", "destroy"));
    }
}

