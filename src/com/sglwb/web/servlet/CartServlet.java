package com.sglwb.web.servlet;

import com.sglwb.bean.Cart;
import com.sglwb.bean.CartItem;
import com.sglwb.bean.ProductBean;
import com.sglwb.bean.UserBean;
import com.sglwb.service.ProductService;
import com.sglwb.service.serviceImpl.ProduceServiceImpl;
import com.sglwb.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
    //addToCard
    public String addToCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //判断用户是否登录过
//        UserBean user = (UserBean) request.getSession().getAttribute("loginUser");
//        if (null == user) {
//            request.setAttribute("msg", "请登录后在购买商品");
//            return "/jsp/login.jsp";
//        }

        String pid = request.getParameter("pid");
        int num = Integer.parseInt(request.getParameter("num"));

        ProductService ps = new ProduceServiceImpl();
        ProductBean product = ps.findProductByPid(pid);
        CartItem cartItem = new CartItem();
        cartItem.setNum(num);
        cartItem.setProductBean(product);

        //获取购物车,从session中获取
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //将商品放置在购物车上
        cart.addCart(cartItem);
        //重定向到cart.jsp
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }

    //deleteCart
    public String deleteCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pid = request.getParameter("pid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.delCart(pid);
        //重定向到cart.jsp
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }
    //clearCart
    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clearCart();
        //重定向到cart.jsp
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }
}
