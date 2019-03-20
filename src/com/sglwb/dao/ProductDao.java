package com.sglwb.dao;

import com.sglwb.bean.ProductBean;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<ProductBean> finNewProducts() throws Exception;

    List<ProductBean> finHotProducts() throws Exception;
}
