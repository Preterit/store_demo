package com.sglwb.dao;

import com.sglwb.bean.Order;
import com.sglwb.bean.OrderItem;
import com.sglwb.bean.UserBean;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order)throws Exception;

    void saveOrderItem(OrderItem orderItem)throws Exception;

    int findTotalRecordsByUid(UserBean user)throws Exception;

    List<Order> findOrderByUidWithPage(UserBean user, int startIndex, int pageSize) throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    int findTotalRecords()throws Exception;

    List<Order> findOrderWithPage(int startIndex, int pageSize)throws Exception;

    int findTotalRecordsByState(int state)throws Exception;

    List<Order> findOrdersBystateWithPage(int state, int startIndex, int pageSize)throws Exception;
}
