package com.sglwb.bean;

public class CartItem {

    private ProductBean productBean;
    private double subTotal;
    private int num;

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public double getSubTotal() {
        return productBean.getShop_price()*num;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productBean=" + productBean +
                ", subTotal=" + subTotal +
                ", num=" + num +
                '}';
    }
}
