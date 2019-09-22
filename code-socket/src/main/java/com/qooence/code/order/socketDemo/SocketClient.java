package com.qooence.code.order.socketDemo;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * <p>
 * 1.创建Socket对象，指定服务端的地址和端口号
 * <p>
 * 2.建立连接后，通过输出输入流进行读写操作
 * <p>
 * 3.通过输出输入流获取服务器返回信息
 * <p>
 * 4.关闭相关资源
 */
public class SocketClient {


    public static void main(String[] args) {

        // 1.创建Socket对象，指定服务端的地址和端口号

        // 2.建立连接后，通过输出输入流进行读写操作

        // 3.通过输出输入流获取服务器返回信息

        // 4.关闭相关资源
        try {
            //创建Socket对象
            Socket socket = new Socket("localhost", 8888);
            //获取输出流,向服务端发送消息
            OutputStream outputStream = socket.getOutputStream();
            //打印流
            PrintWriter printWriter = new PrintWriter(outputStream);

            printWriter.println("Hello,Socket");
            printWriter.flush();

            // 关闭输出流
            socket.shutdownOutput();

            // 获取一个输入流，接收服务端的信息
            InputStream inputStream = socket.getInputStream();
            // 包装成字符流，提高效率
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String info = "";
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("客户端接收服务端发送的信息：" + info);
            }

            // 关闭相关资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
