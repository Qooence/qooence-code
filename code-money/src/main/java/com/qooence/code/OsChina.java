package com.qooence.code;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OsChina {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    // 使用ConcurrentMap存储cookie信息，因为数据在内存中，所以只在程序运行阶段有效，程序结束后即清空
                    private ConcurrentMap<String,List<Cookie>> storage = new ConcurrentHashMap<>();
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
                        return list == null ? new ArrayList<Cookie>() : list;
                    }
                })
                .build();


        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

        // 模拟表单登录
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/user/hash_login?from=")
                .post(new FormBody.Builder()
                    .add("email","1044212759@qq.com")
                    .add("pwd","Sony7namespace")
                    .add("verifyCode", "")
                    .add("save_login", "1")
                    .build())
                .addHeader("User-Agent",userAgent)
                .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
//            log.in
            System.out.println("code = " + response.code());
            System.out.println("message = " + response.message());
//            log.info("code = {}, message = {}", response.code(), response.message());
            return;
        }else {
//            log.info("登录成功 !");
            System.out.println("登录成功");
        }

        // 请求包含用户状态信息的网页，观察能否正确请求并取得状态信息
        request = new Request.Builder()
                .url("https://www.oschina.net/")
                .addHeader("User-Agent", userAgent)
                .get()
                .build();

        // 执行GET请求，并在异步回调中处理请求
        response = client.newCall(request).execute();

        // 打印登录用户名，验证是否获取到了用户的登录信息(状态信息)
        if (response.isSuccessful()) {
            String content = response.body().string();

            Matcher matcher = Pattern.compile("<span class=\"name\">(.*)</span>，您好&nbsp;").matcher(content);
            if (matcher.find()) {
//                log.info("登录用户：{}", matcher.group(1));
                System.out.println("登录用户：" + matcher.group(1));
            } else {
//                log.info("用户未登录");
                System.out.println("用户未登录");
            }

        }
    }
}
