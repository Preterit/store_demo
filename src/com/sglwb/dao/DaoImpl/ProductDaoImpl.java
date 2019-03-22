package com.sglwb.dao.DaoImpl;

import com.sglwb.bean.ProductBean;
import com.sglwb.dao.ProductDao;
import com.sglwb.utils.JDBCUtils;
import com.sglwb.utils.PageModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<ProductBean> finNewProducts() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
//        String sql = "select * from product where pflag = 0 order by desc limit 0 , 9";
        String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
        return runner.query(sql, new BeanListHandler<ProductBean>(ProductBean.class));
    }

    @Override
    public List<ProductBean> finHotProducts() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
//        String sql = "select * from product where pflag = 0 and is_hot = 1 order by desc limit 0 , 9";
        return runner.query(sql, new BeanListHandler<ProductBean>(ProductBean.class));
    }

    @Override
    public List<ProductBean> findProductsWithCidAndPage(String cid, int curNum, int pageSize) throws Exception {
        String sql = "select * from product where cid=? limit ? , ?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<ProductBean>(ProductBean.class), cid, curNum, pageSize);
    }

    @Override
    public int findTotalNum(String cid) throws Exception {
        String sql = "select count(*) from product where cid = ?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query(sql, new ScalarHandler(), cid);
        return num.intValue();
    }

    @Override
    public ProductBean findProductByPid(String pid) throws Exception {
        String sql = "select * from product where pid=?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query(sql, new BeanHandler<ProductBean>(ProductBean.class), pid);
    }
}
