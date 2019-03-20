package com.sglwb.web.servlet;

import com.sglwb.bean.UserBean;
import com.sglwb.service.UserService;
import com.sglwb.service.serviceImpl.UserServiceImpl;
import com.sglwb.utils.CookUtils;
import com.sglwb.utils.DateUtil;
import com.sglwb.utils.UUIDUtils;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sglwb.utils.DateUtil.DATE_FORMAT_YYYY_MM_DD;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/register.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie ck = CookUtils.getCookieByName("remUser",request.getCookies());
        if (null!=ck){
            request.setAttribute("remUser",ck.getValue());
        }
        return "/jsp/login.jsp";
    }

    public String userRegist(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String telephone = request.getParameter("telephone");
            String sex = request.getParameter("sex");

            UserBean user = new UserBean(UUIDUtils.getId(), username, password, name, email, telephone, DateUtil.parseStrToDate(birthday, DATE_FORMAT_YYYY_MM_DD), sex, 0, UUIDUtils.getCode());
            System.out.println(user.toString());

            UserService userService = new UserServiceImpl();
            userService.register(user);

            request.setAttribute("msg", "用户注册成功,请登录!");
        } catch (Exception e) {
            request.setAttribute("msg", "用户注册失败,请重新注册!");
            e.printStackTrace();
        }
        return "/jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);

        UserService service = new UserServiceImpl();
        UserBean userBean = service.userLogin(user);
        if (null != userBean) {
            //登录成功,向session中放入用户信息,重定向到首页
            request.getSession().setAttribute("loginUser", userBean);

            //在登录成功的基础上,判断用户是否选中自动登录复选框
            String autoLogin = request.getParameter("autoLogin");
            if (null != autoLogin) {
                //用户选中复选框
                Cookie ck = new Cookie("autoLogin", userBean.getUsername() + "#" + userBean.getPassword());
                ck.setPath("/store_demo");
                ck.setMaxAge(60 * 60 * 60 * 7);
                response.addCookie(ck);
            }
            //在登录成功的基础上,判断用户是否记住密码
            String remUser = request.getParameter("remUser");
            if (null != remUser) {
                Cookie ck = new Cookie("remUser", userBean.getUsername());
                ck.setPath("/store_demo");
                ck.setMaxAge(60 * 60 * 60 * 7);
                response.addCookie(ck);
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //登录失败,把登录信息放到msg展示
            request.setAttribute("msg", "用户名和密码不匹配!");
            return "/jsp/login.jsp";
        }
        return null;
    }
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用户退出,清空Session
        request.getSession().invalidate();
        Cookie ck = CookUtils.getCookieByName("autoLogin",request.getCookies());
        if (null!=ck){
            ck.setPath("/store_demo");
            ck.setMaxAge(0);
            response.addCookie(ck);
        }
        response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        return null;
    }
}
