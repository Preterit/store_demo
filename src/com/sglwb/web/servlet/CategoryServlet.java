package com.sglwb.web.servlet;

import com.sglwb.bean.CategoryBean;
import com.sglwb.service.CategoryService;
import com.sglwb.service.serviceImpl.CategoryServiceImpl;
import com.sglwb.utils.JedisUtils;
import com.sglwb.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        String json="";

//        Jedis jedis = JedisUtils.getJedis();
//        json = jedis.get("allCats");
        if (null==json||"".equals(json)){
//            System.out.println("缓存中没有数据");
            CategoryService cs = new CategoryServiceImpl();
            List<CategoryBean> allCats = cs.findAllCats();
            if (null!=allCats){
                //将集合中的所有分类信息转换为JSON格式的字符串数据
                json = JSONArray.fromObject(allCats).toString();
                //保存到jedis数据库中
//                jedis.set("allCats",json);
            }
        }else{
//            System.out.println("缓存中有数据");
        }
        response.getWriter().write(json);
        return null;
    }
}
