package com.qooence.code.distrubuteLock.javaApiLock;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 创建回话
 */
public class ZookpeerClient {
    private final static String CONNECTSTRING = "192.168.74.128:2181,192.168.74.129:2181,192.168.74.130:2181,192.168.74.131:2181";

    private static int sessionTimeout=5000;

    public static ZooKeeper getInstance() throws IOException, InterruptedException {
        final CountDownLatch conectStatus = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getState() == Event.KeeperState.SyncConnected){
                    conectStatus.countDown();
                }
            }
        });
        conectStatus.await();
        return zooKeeper;
    }

    public static int getSessionTimeout() {
        return sessionTimeout;
    }
}
