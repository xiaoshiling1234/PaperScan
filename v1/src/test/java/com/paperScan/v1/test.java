package com.paperScan.v1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String json="{\"userid\":\"xxx\",\"data\":[{\"name\":\"xxx\",\"phone\":\"xxx\"}]}";
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<JsonBean>() {}.getType();
        JsonBean jsonBean = gson.fromJson(json, type);
        System.out.println(jsonBean.userid);
        for (JsonBean.data i :jsonBean.data){
            System.out.println(i.name+"-"+i.phone);
        }
    }

    static class JsonBean{
        public String userid;
        public List<data> data ;
        static class data{
            public String name;
            public String phone;
        }
    }
}
