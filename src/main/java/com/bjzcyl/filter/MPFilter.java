package com.bjzcyl.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by admin on 2016/10/19.
 */
public class MPFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String s=req.getRequestURL().toString();
        String filename=s.substring(s.lastIndexOf("/"), s.length());
        String filePath=req.getSession().getServletContext().getRealPath(filename);
        HttpServletResponse res = (HttpServletResponse) response;
        if(s.contains("php")){
            res.setStatus(404);
            request.getRequestDispatcher("/404.jsp").forward(request,response);
            chain.doFilter(request,response);
            return;
        }
        Path p = Paths.get(filePath);
        if(p.toFile().exists()){
            res.setStatus(200);
            OutputStream out = res.getOutputStream();
            out.write(Files.readAllBytes(p));
            out.close();
        }else {
            res.setStatus(404);
            request.getRequestDispatcher("/404.jsp").forward(request,response);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
