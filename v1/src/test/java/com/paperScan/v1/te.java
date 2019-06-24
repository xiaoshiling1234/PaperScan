package com.paperScan.v1;

public class te {
    public static void main(String[] args) {
        String str="[{\"userid\":861910040797845, \"data\":[{\"name\":xxxxx,\"phone\":xxxxxxx}]}]";
        str=str.replaceAll("\"userid\":", "\"userid\":\"");
        str=str.replaceAll(",\"data\"", "\",\"data\"");
        str=str.replaceAll(",\"phone\":", "\",\"phone\":\"");
        str=str.replaceAll("\"name\":", "\"name\":\"");
        str=str.replaceAll("},", "\"},");
        str=str.replaceAll("}]", "\"}]");


        System.out.println(str);
    }
}
