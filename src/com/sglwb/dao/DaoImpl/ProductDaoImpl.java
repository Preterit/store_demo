package com.sglwb.dao.DaoImpl;

import com.sglwb.bean.ProductBean;
import com.sglwb.dao.ProductDao;
import com.sglwb.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<ProductBean> finNewProducts() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
//        String sql = "select * from product where pflag = 0 order by desc limit 0 , 9";
        String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
        return runner.query(sql, new BeanListHandler<ProductBean>(ProductBean.class));
    }

    @Override
    public List<ProductBean> finHotProducts() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
//        String sql = "select * from product where pflag = 0 and is_hot = 1 order by desc limit 0 , 9";
        return runner.query(sql, new BeanListHandler<ProductBean>(ProductBean.class));
    }
}
