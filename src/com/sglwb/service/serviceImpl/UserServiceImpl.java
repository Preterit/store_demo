package com.sglwb.service.serviceImpl;

import com.sglwb.bean.UserBean;
import com.sglwb.dao.DaoImpl.UserDaoImpl;
import com.sglwb.dao.UserDao;
import com.sglwb.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void register(UserBean user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.register(user);
    }

    @Override
    public UserBean userLogin(UserBean user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        return userDao.userLogin(user);
    }
}
