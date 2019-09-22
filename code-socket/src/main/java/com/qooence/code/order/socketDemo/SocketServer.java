package com.qooence.code.order.socketDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务端 (TCP/IP) 单播(点对点)
 * <p>
 * 1.创建ServerSocket对象，绑定并监听端口
 * <p>
 * 2.通过accept监听客户端的请求
 * <p>
 * 3.建立连接后，通过输出输入流进行读写操作
 * <p>
 * 4.关闭相关资源
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务端已启动,等待连接...");

            Socket socket = serverSocket.accept();//侦听并接受到此套接字的连接，返回一个Socket对象

            // 根据输入输出流和客户端连接

            //得到一个输入流，接收客户端传递的信息
            InputStream inputStream = socket.getInputStream();
            // 提高效率，将自己的字节流转为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //加入缓存区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tem;
            String info = "";
            while ((tem = bufferedReader.readLine()) != null) {
                info += tem;
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端IP" + socket.getInetAddress().getHostAddress());
            }

            // 获取一个输出流，向客户端发送信息
            OutputStream outputStream = socket.getOutputStream();
            // 将输出流包装成打印流
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("你好，服务端已接收到你的信息");
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流

            //关闭相应的资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
