package com.qooence.code;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 单列 OkHttpClient
 */
public class OkHttpClientFactory {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientFactory.class);

    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

    private static OkHttpClient client;


    private static OkHttpClient getInstance(){
        if (client == null) {
            synchronized (OkHttpClientFactory.class) {
                if (client == null) {

                    // Log信息拦截器
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    //设置日志打印级别
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

                    client = new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS) // 连接超时设置
                            .readTimeout(30, TimeUnit.SECONDS) // 读取超时设置
                            .writeTimeout(30, TimeUnit.SECONDS) // 写入超时设置
                            .addInterceptor(loggingInterceptor) //日志拦截器
                            .cookieJar(new CookieJar() { // cookies 处理
                                // 使用ConcurrentMap存储cookie信息，因为数据在内存中，所以只在程序运行阶段有效，程序结束后即清空
                                private ConcurrentMap<String, List<Cookie>> storage = new ConcurrentHashMap<>();
                                @Override
                                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                                    String host = httpUrl.host();
                                    if(null != list && !list.isEmpty()){
                                        storage.put(host,list);
                                    }
                                }

                                @Override
                                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                                    String host = httpUrl.host();
                                    List<Cookie> list = storage.get(host);
                                    logger.info("cookies.loadForRequest ==> host->[{}]",host);
                                    return list == null ? new ArrayList<Cookie>() : list;
                                }
                            })
                            .addInterceptor(new TokenInterceptor()) // token 拦截器
                            .build();
                }
            }
        }
        return client;
    }

    public static String doGet(String host, String path, Map<String, String> headers, Map<String, String> querys) throws Exception {
        StringBuffer url = new StringBuffer(host + (path == null?"":path));
        if(querys != null) {
            url.append("?");
            Iterator iterator = querys.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> e = (Map.Entry)iterator.next();
                url.append(e.getKey()).append("=").append(e.getValue() + "&");
            }
            url = new StringBuffer(url.substring(0,url.length()-1));
        }
        Request.Builder requestBuilder = new Request.Builder();
        if(headers != null && headers.size() > 0) {
            Iterator iterator = headers.keySet().iterator();
            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        Request request = (requestBuilder).url(url.toString()).build();
        client = getInstance();
        Response response = client.newCall(request).execute();
        String responseStr = response.body() == null ? "" : response.body().string();
        return responseStr;
    }


    public static Response doPost(String url){
        String json = "{" +
                        "'loginType':'1',"
                    +   "'pwdOrVerifyCode':'jyq2KH98173156',"
                    +   "'verifyCode':'1044212759@qq.com',"
                    +   "'uaToken':'120#bX1bSwIdI6hVLJRxm1Y0HDnZbIPShG0lP597XFy/FphV+jazD2RZ3m/JIeIBnWSH7W7COnVfdQmcN7jFbM3Tf1WLLX2XK1lufKC0HOmt/HHt9p0tFAvbe17lQeMApxlP1BXT0oGSXEiMEIM98vSm2gSEUb/18V1Zf8l/7OzWLPoffNzONNa2IBKAwHY0cgVfBSphzsKCrX/aeG1zDYSqKjuJbvY3GNoFnmtm/dblQjDiMwrAyFSyffcsqKzkqAAETj3Mt1x5K1OvAl4+FBJdC5WvK1OudMfhhDsZ9QPLZDxvV/v/S5eYO73vVxGnzevQPY7iBLY5j2eozwi7gMq7y3GhhH6YhC/9+EcebSuKaUt5yEpSb5htLqPeybYb75UyNPc8CoYy3v87xO6ob1G74JiugnfT9vu/qKOLAHG7n91VrOscFoejKH7kXjI/kwKt7ll6/9RLBssLPH5sBG+izK+SjI66XqVFlfXy5PJXMR8zjlsc2+iDXwbJYTnPbqvVFUXMGY4ZgVF+3+MoeNYuG50SPVImDhmEZsx+nrJNYwyDGF0zlKIK7GnEQNKGS8U0xMvK/RtG9k3zrZ+qUENBK1nIZqVhgjRa96YmQV6/q129uIQ8ZL+b6mY0GVVxft9OncQYjVIexF0RuPWLoxkzKqEnMN7vSa3dGjs3DGhMyJKBshUwFKPr8IqKF+VyGZUvHt40J1CH4umuJNT5ZQy4zENIrpf9aIqbQHmpdkMT0X/IfEWOGuJ0ig6bb3979YMohdrx8jWojzUM6NlM6iJOntqbVyCpLl74BfucGp52R8tl1Yl1Iz6cpC3pbZP2BUS/jpmIU/Xd3NzXRU3gdst49ZG9TVrEOGMF729AlgUI8TAl',"
                    +   "'webUmidToken':'T2CE14AFF00B6DFDC107C6372496A679A37506E48D476ADA47B23467365'"
                    + "}";

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 执行模拟登录请求
        Response response = null;
        try {
            client = getInstance();
            response = client.newCall(request).execute();
            final String res = response.body().string();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) {
            System.out.println("code = {" +response.code() + "}, message = {"+response.message()+"}");
        } else {
            System.out.println("成功");
        }
        return response;
    }

}
