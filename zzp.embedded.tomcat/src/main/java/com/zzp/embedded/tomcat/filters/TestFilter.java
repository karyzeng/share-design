package com.zzp.embedded.tomcat.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Description 测试Filter
 * @Author karyzeng
 * @since 2020.08.07
 **/
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> enumeration = filterConfig.getInitParameterNames();
        if (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            System.out.println(String.format("Filter init: init-param name:[%s], value:[%s]", name, filterConfig.getInitParameter(name)));
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(String.format("Filter doFilter: remote host: [%s]", request.getRemoteHost()));
        chain.doFilter(request, response);
    }
}
