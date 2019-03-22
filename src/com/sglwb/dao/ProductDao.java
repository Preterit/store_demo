package com.sglwb.dao;

import com.sglwb.bean.ProductBean;
import com.sglwb.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<ProductBean> finNewProducts() throws Exception;

    List<ProductBean> finHotProducts() throws Exception;

    List<ProductBean> findProductsWithCidAndPage(String cid, int curNum,int pageSize) throws Exception;

    int findTotalNum(String cid)throws Exception;

    ProductBean findProductByPid(String pid)throws Exception;
}
