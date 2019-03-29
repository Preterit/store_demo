package com.sglwb.dao.DaoImpl;

import com.sglwb.bean.Order;
import com.sglwb.bean.OrderItem;
import com.sglwb.bean.ProductBean;
import com.sglwb.bean.UserBean;
import com.sglwb.dao.OrderDao;
import com.sglwb.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void saveOrder(Order order) throws Exception {
        String sql = "insert INTO orders VALUES(?,?,?,?,?,?,?,?)";
        Object[] params = {order.getOid(), order.getOrderTime(), order.getTotal(), order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
        QueryRunner qr = new QueryRunner();
        qr.update(JDBCUtils.getConnection(), sql, params);
    }

    @Override
    public void saveOrderItem(OrderItem item) throws Exception {
        String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
        Object[] params = {item.getItemid(), item.getQuantity(), item.getTotal(), item.getProduct().getPid(), item.getOrder().getOid()};
        QueryRunner runner = new QueryRunner();
        runner.update(JDBCUtils.getConnection(), sql, params);
    }

    @Override
    public int findTotalRecordsByUid(UserBean user) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM orders WHERE uid=?";
        Long num = (Long) runner.query(sql, new ScalarHandler(), user.getUid());
        return num.intValue();
    }

    @Override
    public List<Order> findOrderByUidWithPage(UserBean user, int startIndex, int pageSize) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where uid =? order by ordertime desc limit ? , ?";
        List<Order> list = runner.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
        for (Order order : list) {
            sql = "SELECT * FROM orderitem o ,product p WHERE o.pid = p.pid AND oid = ?";
            List<Map<String, Object>> list01 = runner.query(sql, new MapListHandler(), order.getOid());
            getOrderItemList(order, list01);
        }
        return list;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM orders WHERE oid = ?";
        Order order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);

        sql = "SELECT * FROM orderitem o ,product p WHERE o.pid = p.pid AND oid=?";
        List<Map<String, Object>> list = runner.query(sql, new MapListHandler(), oid);
        getOrderItemList(order, list);
        return order;
    }

    @Override
    public int findTotalRecords() throws Exception {
        String sql = "SELECT COUNT(*) FROM orders";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query(sql, new ScalarHandler());
        return num.intValue();
    }

    @Override
    public List<Order> findOrderWithPage(int startIndex, int pageSize) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM orders ORDER BY ordertime DESC LIMIT ?,?";
        List<Order> list = runner.query(sql, new BeanListHandler<Order>(Order.class), startIndex, pageSize);
//        for (Order order : list) {
//            sql = "SELECT * FROM orderitem o ,product p WHERE o.pid = p.pid AND oid =?";
//            List<Map<String, Object>> list1 = runner.query(sql, new MapListHandler(),order.getOid());
//            getOrderItemList(order,list1);
//        }
        return list;
    }

    @Override
    public int findTotalRecordsByState(int state) throws Exception {
        String sql = "SELECT COUNT(*) FROM orders WHERE state = ?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query(sql, new ScalarHandler(), state);
        return num.intValue();
    }

    @Override
    public List<Order> findOrdersBystateWithPage(int state, int startIndex, int pageSize)throws Exception {
        String sql = "SELECT * FROM orders WHERE state = ? ORDER BY ordertime DESC LIMIT ?,?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        List<Order> list = runner.query(sql, new BeanListHandler<Order>(Order.class), state, startIndex, pageSize);
        return list;
    }

    /**
     * @param order
     * @param list
     */
    private void getOrderItemList(Order order, List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            ProductBean product = new ProductBean();
            OrderItem orderItem = new OrderItem();
            try {
                BeanUtils.populate(product, map);
                BeanUtils.populate(orderItem, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderItem.setProduct(product);
            order.getList().add(orderItem);
        }
    }
}
