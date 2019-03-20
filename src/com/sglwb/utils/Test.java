package com.sglwb.utils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        test01();
//        test02();

    }

    private static void test02() {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "update user set state = ? where username = ?";
        try {
            runner.update(sql, 1, "aaa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void test01() {
        Map<String, String[]> map = new HashMap<>();
        map.put("name", new String[]{"垃圾1"});
        map.put("password", new String[]{"垃圾2"});
        map.put("phone", new String[]{"垃圾3"});
        map.put("email", new String[]{"垃圾4"});
        Bean bean = new Bean();
        MyBeanUtils.populate(bean, map);

        System.out.println(bean.toString());
    }

    static class Bean {
        private String name;
        private String password;
        private String phone;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Bean(String name, String password, String phone, String email) {
            this.name = name;
            this.password = password;
            this.phone = phone;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public Bean() {
        }
    }

}
