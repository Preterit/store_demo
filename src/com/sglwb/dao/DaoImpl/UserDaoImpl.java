package com.sglwb.dao.DaoImpl;

import com.sglwb.bean.UserBean;
import com.sglwb.dao.UserDao;
import com.sglwb.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements UserDao {
    @Override
    public void register(UserBean user) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        Object[] param = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        runner.update(sql, param);
    }

    @Override
    public UserBean userLogin(UserBean user) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username=? and password = ?";
        Object[] param = {user.getUsername(), user.getPassword()};
        return runner.query(sql, new BeanHandler<UserBean>(UserBean.class), param);
    }
}
