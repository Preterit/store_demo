package com.sglwb.web.servlet;

import com.sglwb.bean.*;
import com.sglwb.service.OrderService;
import com.sglwb.service.serviceImpl.OrderServiceImpl;
import com.sglwb.utils.PageModel;
import com.sglwb.utils.UUIDUtils;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();
    public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户信息
        UserBean user = (UserBean) request.getSession().getAttribute("loginUser");
        //获取cart信息
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //创建订单
        Order order = new Order();
        //为订单对象赋予值: oid,orderTime,state
        order.setOid(UUIDUtils.getId());
        order.setOrderTime(new Date());
        order.setState(1);
        //获取到购物车中的总计为订单对象下的总计赋值
        order.setTotal(cart.getTotal());
        //为订单关联用户
        order.setUser(user);
        for (CartItem item : cart.getCartItems()) {
            OrderItem oi = new OrderItem();
            oi.setItemid(UUIDUtils.getId());

            oi.setProduct(item.getProductBean());
            oi.setTotal(item.getSubTotal());
            oi.setQuantity(item.getNum());

            oi.setOrder(order);
            order.getList().add(oi);
        }
        orderService.saveOrder(order);
        //清空购物车
        cart.clearCart();
        //向request内放置一份order,便于在订单详情页面显示订单信息
        request.setAttribute("order", order);
        return "/jsp/order_info.jsp";
    }

    public String findOrdersByUidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前页
        int num = Integer.parseInt(request.getParameter("num"));
        UserBean user = (UserBean) request.getSession().getAttribute("loginUser");
        //调用业务层返回pageModel信息
        PageModel pm = orderService.findOrdersByUidWithPage(user, num);
        //将PageModel对象放入request,转发到order_list.jsp页面
        request.setAttribute("page",pm);
        return "/jsp/order_list.jsp";
    }
    //findOrderByOid
    public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oid = request.getParameter("oid");
        Order order = orderService.findOrderByOid(oid);
        request.setAttribute("order",order);
        return "/jsp/order_info.jsp";
    }
}
