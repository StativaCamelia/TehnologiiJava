package com.example.laborator2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter that logs request info
 */
@WebFilter(urlPatterns={"/input.jsp"} , filterName="LogFilter")
public class LogFilter implements Filter {

    private FilterConfig filterConfigObj = null;

    public void init(FilterConfig filterConfigObj) {
        this.filterConfigObj = filterConfigObj;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String remoteAddress =  request.getRemoteAddr();
        String protocol = request.getProtocol();

        System.out.println(protocol);
        System.out.println(remoteAddress);

        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
