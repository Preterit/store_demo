package com.sglwb.service;

import com.sglwb.bean.CategoryBean;

import java.util.List;

public interface CategoryService {
    List<CategoryBean> findAllCats() throws Exception;

    void saveCategory(CategoryBean category)throws Exception;
}
