package com.qooence.code.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MasterChooseTest3 {

    private final static String CONNECTSTRING = "192.168.74.128:2181,192.168.74.129:2181,192.168.74.130:2181,192.168.74.131:2181";

    public static void main(String[] args) throws IOException {
        MasterSelector selector = null;
        try{
            ZkClient zkClient = new ZkClient(CONNECTSTRING,5000,5000,
                    new SerializableSerializer());
            UserCenter userCenter = new UserCenter();
            userCenter.setMc_id(3);
            userCenter.setMc_name("客户端3");

            selector = new MasterSelector(userCenter,zkClient);
            while (true){
                selector.start(); // 触发选举操作
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            selector.stop();
        }
    }
}
