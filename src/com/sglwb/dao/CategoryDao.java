package com.sglwb.dao;

import com.sglwb.bean.CategoryBean;

import java.util.List;

public interface CategoryDao {
    List<CategoryBean> findAllCats() throws Exception;

    void saveCategory(CategoryBean category)throws Exception;
}
