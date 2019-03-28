package com.sglwb.service;

import com.sglwb.bean.ProductBean;
import com.sglwb.utils.PageModel;

import java.util.List;

public interface ProductService {
    List<ProductBean> finNewProducts() throws Exception;

    List<ProductBean> finHotProducts() throws Exception;

    PageModel findProductsWithCidAndPage(String cid, int curNum) throws Exception;

    ProductBean findProductByPid(String pid) throws Exception;

    PageModel findAllProducts(int curNum, int pageSize) throws Exception;

    PageModel findAllSoldOutProducts(int curNum, int i)throws Exception;

    void saveProduct(ProductBean product)throws Exception;

}
