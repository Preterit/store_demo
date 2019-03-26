package com.sglwb.web.filter;

import com.sglwb.bean.UserBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "PrivilegeFilter")
public class PrivilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        UserBean user = (UserBean) request.getSession().getAttribute("loginUser");
        if (null==user){
            req.setAttribute("msg", "请登录后在访问");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
