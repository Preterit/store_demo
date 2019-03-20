package com.sglwb.web.servlet;

import com.sglwb.bean.ProductBean;
import com.sglwb.service.ProductService;
import com.sglwb.service.serviceImpl.ProduceServiceImpl;
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
        return null;
    }
}
