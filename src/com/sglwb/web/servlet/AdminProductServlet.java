package com.sglwb.web.servlet;

import com.sglwb.bean.CategoryBean;
import com.sglwb.bean.ProductBean;
import com.sglwb.service.CategoryService;
import com.sglwb.service.ProductService;
import com.sglwb.service.serviceImpl.CategoryServiceImpl;
import com.sglwb.service.serviceImpl.ProduceServiceImpl;
import com.sglwb.utils.PageModel;
import com.sglwb.utils.UUIDUtils;
import com.sglwb.utils.UploadUtils;
import com.sglwb.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    public String findAllProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int curNum = Integer.parseInt(request.getParameter("num"));
        ProductService productService = new ProduceServiceImpl();
        PageModel pm = productService.findAllProducts(curNum, 5);

        request.setAttribute("page", pm);
        return "admin/product/list.jsp";
    }

    public String findAllSoldOutProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int curNum = Integer.parseInt(request.getParameter("num"));
        ProductService productService = new ProduceServiceImpl();
        PageModel pm = productService.findAllSoldOutProducts(curNum, 5);
        request.setAttribute("page", pm);
        return "admin/product/pushDown_list.jsp";
    }

    public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //_服务端查询到所有分类,
        CategoryService categoryService = new CategoryServiceImpl();
        List<CategoryBean> allCats = categoryService.findAllCats();
        request.setAttribute("allcats", allCats);
        return "admin/product/add.jsp";
    }

    public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //_服务端查询到所有分类,
        ProductBean product = new ProductBean();
        Map<String, String> map = new HashMap<String, String>();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) { // 普通项
                    map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                } else {  //上传项
                    //获取原文件名
                    String name = fileItem.getName();
                    //创建图片存储的真实路径
                    String realPath = getServletContext().getRealPath("/products/3/");
                    System.out.println("图片存储路径==" + realPath);
                    //声明一个目录
                    File newFile = new File(realPath);
                    if (!newFile.exists()) {
                        newFile.mkdir();
                    }
                    //创建文件
                    File finalFile = new File(newFile, name);
                    if (!finalFile.exists()) {
                        finalFile.createNewFile();
                    }
//                    //流对接
                    //建立和空文件对应的输出流
                    OutputStream os = new FileOutputStream(finalFile);
                    InputStream is = fileItem.getInputStream();
                    IOUtils.copy(is, os);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
                    map.put("pimage", "/products/3/" + name);
                }
            }
            BeanUtils.populate(product, map);
            product.setPid(UUIDUtils.getId());
            product.setPflag(0);
            product.setPdate(new Date());
            ProductService productService = new ProduceServiceImpl();
            productService.saveProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(getServletContext().getContextPath() + "/AdminProductServlet?method=findAllProducts&num=1");
        return null;
    }
}
