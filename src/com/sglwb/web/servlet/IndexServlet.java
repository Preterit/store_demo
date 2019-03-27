package com.sglwb.web.servlet;

import com.sglwb.bean.ProductBean;
import com.sglwb.bean.UserBean;
import com.sglwb.service.ProductService;
import com.sglwb.service.UserService;
import com.sglwb.service.serviceImpl.ProduceServiceImpl;
import com.sglwb.service.serviceImpl.UserServiceImpl;
import com.sglwb.utils.CookUtils;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductService ps = new ProduceServiceImpl();
        //获取最新的9条商品
        List<ProductBean> newsPd = ps.finNewProducts();
        //获取最热的9条商品
        List<ProductBean> hotsPd = ps.finHotProducts();
        //将商品放到request中
        request.setAttribute("news", newsPd);
        request.setAttribute("hots", hotsPd);
        //转发到首页
        return "/jsp/index.jsp";
    }
}
