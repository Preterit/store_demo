package com.sglwb.web.servlet;

import com.sglwb.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
    public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
