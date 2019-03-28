package com.sglwb.web.servlet;

import com.sglwb.bean.CategoryBean;
import com.sglwb.service.CategoryService;
import com.sglwb.service.serviceImpl.CategoryServiceImpl;
import com.sglwb.utils.UUIDUtils;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryServiceImpl();

    public String findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CategoryBean> allCats = categoryService.findAllCats();
        request.setAttribute("cats", allCats);
        return "admin/category/list.jsp";
    }

    public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "admin/category/add.jsp";
    }

    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cname = request.getParameter("cname");
        CategoryBean category = new CategoryBean();
        category.setCid(UUIDUtils.getId());
        category.setCname(cname);
        categoryService.saveCategory(category);
        response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAllCategory");
        return null;
    }

}
