package com.example.laborator2.filter;

import com.example.laborator2.filter.utils.CharResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Decorator for the servlet response. The decorator will add a text before and after the response
 */
@WebFilter(urlPatterns={"/*"} , filterName="DecorateFilter")
public class DecorateFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        PrintWriter out = servletResponse.getWriter();
        CharResponseWrapper responseWrapper = new CharResponseWrapper(
                (HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, responseWrapper);

        String response = responseWrapper.toString();

        out.write("filtered" +response + " filtered");
    }

    @Override
    public void destroy() {

    }
}
