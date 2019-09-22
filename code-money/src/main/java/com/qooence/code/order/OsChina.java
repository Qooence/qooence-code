package com.qooence.code.order;

public class OsChina {

    public static void main(String[] args) {

        try {
//            String res = OkHttpClientFactory.doGet("https://passport.csdn.net","/login",null,null);

            OkHttpClientFactory.doPost("https://passport.csdn.net/v1/register/pc/login/doLogin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
