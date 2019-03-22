package com.sglwb.service.serviceImpl;

import com.sglwb.bean.ProductBean;
import com.sglwb.dao.DaoImpl.ProductDaoImpl;
import com.sglwb.dao.ProductDao;
import com.sglwb.service.ProductService;
import com.sglwb.utils.PageModel;

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

    @Override
    public PageModel findProductsWithCidAndPage(String cid, int curNum)throws Exception {
        ProductDao pd =new ProductDaoImpl();
        int totalNum = pd.findTotalNum(cid);
        PageModel pm = new PageModel(curNum,totalNum,12);
        List<ProductBean> list = pd.findProductsWithCidAndPage(cid,pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);
        pm.setUrl("ProductServlet?method=findProductsWithCidAndPage&cid="+cid);
        return pm;
    }

    @Override
    public ProductBean findProductByPid(String pid) throws Exception {
        ProductDao pd=new ProductDaoImpl();
        return pd.findProductByPid(pid);
    }
}
