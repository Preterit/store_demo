package com.sglwb.web.servlet;

import com.sglwb.bean.Order;
import com.sglwb.service.OrderService;
import com.sglwb.service.serviceImpl.OrderServiceImpl;
import com.sglwb.utils.PageModel;
import com.sglwb.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    public String findOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int curNum = Integer.parseInt(request.getParameter("num"));
        PageModel pm = orderService.findOrdersWithPage(curNum);
        request.setAttribute("page", pm);
        return "admin/order/list.jsp";
    }

    public String findOrderByOidWithAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oid = request.getParameter("oid");
        Order order = orderService.findOrderByOid(oid);
        String jsonStr = JSONArray.fromObject(order.getList()).toString();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonStr);
        return null;
    }
    public String findOrdersBystateWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int curNum = Integer.parseInt(request.getParameter("num"));
        int state = Integer.parseInt(request.getParameter("state"));
        PageModel pm = orderService.findOrdersBystateWithPage(curNum,state);
        request.setAttribute("page",pm);
        return "admin/order/list.jsp";
    }
}
