package com.sglwb.service;

import com.sglwb.bean.Order;
import com.sglwb.bean.UserBean;
import com.sglwb.utils.PageModel;

public interface OrderService {
    void saveOrder(Order order)throws Exception;

    PageModel findOrdersByUidWithPage(UserBean user, int num)throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    PageModel findOrdersWithPage(int curNum)throws Exception;
}
