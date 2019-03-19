package com.sglwb.service;

import com.sglwb.bean.UserBean;

public interface UserService {
    void register(UserBean user) throws Exception;

    UserBean userLogin(UserBean user)throws Exception;
}
