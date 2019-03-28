package com.sglwb.service.serviceImpl;

import com.sglwb.bean.Order;
import com.sglwb.bean.OrderItem;
import com.sglwb.bean.UserBean;
import com.sglwb.dao.DaoImpl.OrderDaoImpl;
import com.sglwb.dao.OrderDao;
import com.sglwb.service.OrderService;
import com.sglwb.utils.JDBCUtils;
import com.sglwb.utils.PageModel;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void saveOrder(Order order) throws Exception {
        try {
            JDBCUtils.startTransaction();
            //先把订单存到数据库中
            orderDao.saveOrder(order);
            for (OrderItem orderItem : order.getList()) {
                //保存每一个订单项
                orderDao.saveOrderItem(orderItem);
            }
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
        }
    }

    @Override
    public PageModel findOrdersByUidWithPage(UserBean user, int num) throws Exception {
        int totalRecords = orderDao.findTotalRecordsByUid(user);// 获取总共的订单数量
        PageModel pm = new PageModel(num, totalRecords, 3); //创建pageModel对象
        List<Order> list = orderDao.findOrderByUidWithPage(user, pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);
        // 关联url
        pm.setUrl("OrderServlet?method=findOrdersByUidWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        Order order = orderDao.findOrderByOid(oid);
        return order;
    }

    @Override
    public PageModel findOrdersWithPage(int curNum)throws Exception {
        int totalRecords = orderDao.findTotalRecords();
        PageModel pm = new PageModel(curNum,totalRecords,5);
        List<Order> list = orderDao.findOrderWithPage(pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);
        pm.setUrl("AdminOrderServlet?method=findOrdersWithPage");
        return pm;
    }
}
