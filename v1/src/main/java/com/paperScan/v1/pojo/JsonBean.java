package com.paperScan.v1.pojo;

import java.util.List;

public class JsonBean {
    public String userid;
    public List<data> data ;
    public static class data{
        public String name;
        public String phone;
    }
}
