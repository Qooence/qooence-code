package com.qooence.code.order.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 一个选主服务，对外提供（选举msater)
 * 多台服务器启动时，争抢注册master
 */
public class MasterSelector {

    private ZkClient zkClient;

    private final static String MASTER_PATH = "/master";//需要争抢的节点

    private IZkDataListener dataListener; // 注册节点内容变化

    private UserCenter server; // 其他服务器

    private UserCenter master; // master节点

    private static boolean isRunning = false;

    // 定时器 java api 提供
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public MasterSelector(UserCenter server, ZkClient zkClient) {
        this.server = server;
        this.zkClient = zkClient;
        System.out.println("[" + server + "] 去争抢master权限");
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 节点如果被删除了，发起选举操作
                chooseMaster();
            }
        };
    }

    public void start() {
        // 开始选主
        if (!isRunning) {
            isRunning = true;
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);//注册节点事件
            chooseMaster();
        }
    }

    public void stop() {
        // 停止
        if (isRunning) {
            isRunning = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
            releaseMaster();
        }
    }

    // 具体选mater的实现逻辑
    private void chooseMaster() {
        if (!isRunning) {
            System.out.println("当前服务没有启动");
            return;
        }
        try {
            zkClient.createEphemeral(MASTER_PATH, server);
            master = server;
            System.out.println(master + "->我现在已经是mater了，你们要听我的！");

            // 定时器
            // 通过mater释放模拟master出现故障
            scheduledExecutorService.schedule(() -> {

                releaseMaster();//释放锁
            }, 5, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) {
            // 表示master已经存在
            UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
            if (userCenter == null) {
                chooseMaster();// 再次获取master
            } else {
                master = userCenter;
            }
        }

    }

    private void releaseMaster() {
        // 释放锁(故障模拟过程)
        // 判断当前是不是master,只有master才需要释放
        if (checkIsMaster()) {
            zkClient.deleteRecursive(MASTER_PATH); // 删除
        }
    }

    private boolean checkIsMaster() {
        // 判断当前的server是不是master
        UserCenter userCenter = zkClient.readData(MASTER_PATH);
        if (userCenter.getMc_name().equals(server.getMc_name())) {
            master = userCenter;
            return true;
        }
        return false;
    }

}
