package com.sglwb.web.servlet;

import com.sglwb.bean.ProductBean;
import com.sglwb.service.ProductService;
import com.sglwb.service.serviceImpl.ProduceServiceImpl;
import com.sglwb.utils.PageModel;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {

    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取传入的商品id
        String pid = request.getParameter("pid");
        //根据pid查询对应商品信息
        ProductService ps = new ProduceServiceImpl();
        ProductBean pro = ps.findProductByPid(pid);
        request.getSession().setAttribute("pro", pro);
        return "/jsp/product_info.jsp";
    }

    //findProductsWithCidAndPage
    public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接受分页信息
        String cid = request.getParameter("cid");
        int curNum = Integer.parseInt(request.getParameter("num"));

        //调用业务层查询当前分类下的当前业的数据
        ProductService ps = new ProduceServiceImpl();
        PageModel pm = ps.findProductsWithCidAndPage(cid, curNum);

        request.getSession().setAttribute("page", pm);

        //请求转发
        return "/jsp/product_list.jsp";
    }
}
