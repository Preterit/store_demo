package com.sglwb.dao.DaoImpl;

import com.sglwb.bean.CategoryBean;
import com.sglwb.dao.CategoryDao;
import com.sglwb.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<CategoryBean> findAllCats() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from category";
        return runner.query(sql, new BeanListHandler<CategoryBean>(CategoryBean.class));
    }

    @Override
    public void saveCategory(CategoryBean category) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into category values(?,?)";
        runner.update(sql,category.getCname());
    }
}
