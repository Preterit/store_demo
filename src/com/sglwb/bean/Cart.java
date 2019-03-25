package com.sglwb.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    //代表购物车上的商品,键:商品id   值:商品
    private Map<String,CartItem> map = new HashMap<String ,CartItem>();

    private double total = 0;
    //添加到购物车
    public void addCart(CartItem item){
        //获取到商品的pid
        String pid = item.getProductBean().getPid();
        if (map.containsKey(pid)){
            CartItem old = map.get(pid);
            old.setNum(old.getNum()+item.getNum());
        }else{
            map.put(pid,item);
        }
    }
    //从购物车删除商品,根据pid
    public void delCart(String pid){
        map.remove(pid);
    }
    //清空购物车
    public void clearCart(){
        map.clear();
    }

    public double getTotal() {
        total = 0;
        for (CartItem item:map.values()) {
            total+=item.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public Collection<CartItem> getCartItems(){
        return map.values();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", total=" + total +
                '}';
    }
}
