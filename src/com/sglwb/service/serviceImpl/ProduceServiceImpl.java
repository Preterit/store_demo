package com.sglwb.service.serviceImpl;

import com.sglwb.bean.ProductBean;
import com.sglwb.dao.DaoImpl.ProductDaoImpl;
import com.sglwb.dao.ProductDao;
import com.sglwb.service.ProductService;

import java.util.List;

public class ProduceServiceImpl implements ProductService {
    @Override
    public List<ProductBean> finNewProducts() throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.finNewProducts();
    }

    @Override
    public List<ProductBean> finHotProducts() throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.finHotProducts();
    }
}
