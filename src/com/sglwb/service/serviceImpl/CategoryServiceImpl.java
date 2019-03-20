package com.sglwb.service.serviceImpl;

import com.sglwb.bean.CategoryBean;
import com.sglwb.dao.CategoryDao;
import com.sglwb.dao.DaoImpl.CategoryDaoImpl;
import com.sglwb.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryBean> findAllCats() throws Exception {
        CategoryDao cd = new CategoryDaoImpl();
        return cd.findAllCats();
    }
}
