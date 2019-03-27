package com.sglwb.web.filter;

import com.sglwb.bean.UserBean;
import com.sglwb.service.UserService;
import com.sglwb.service.serviceImpl.UserServiceImpl;
import com.sglwb.utils.CookUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        //session中有用户登录信息,直接用
        UserBean loginUser = (UserBean) request.getSession().getAttribute("loginUser");
        if (null != loginUser) {
            chain.doFilter(req, resp);
            return;
        }
        //获取用户携带到服务器的cookie对象
        Cookie ck = CookUtils.getCookieByName("autoLogin", request.getCookies());
        if (null != ck) {
            String value = ck.getValue();
            String[] split = value.split("#");
            UserBean userBean = new UserBean();
            userBean.setUsername(split[0]);
            userBean.setPassword(split[1]);

            UserService service = new UserServiceImpl();
            try {
                userBean = service.userLogin(userBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userBean != null) {
                request.getSession().setAttribute("loginUser", userBean);
            }
            chain.doFilter(req, resp);
        } else {
            chain.doFilter(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
