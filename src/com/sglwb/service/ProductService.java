package com.sglwb.service;

import com.sglwb.bean.ProductBean;

import java.util.List;

public interface ProductService {
    List<ProductBean> finNewProducts() throws Exception;

    List<ProductBean> finHotProducts() throws Exception;
}
