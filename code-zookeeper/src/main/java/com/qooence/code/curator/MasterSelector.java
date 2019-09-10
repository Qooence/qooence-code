package com.qooence.code.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class MasterSelector {

    private final static String CONNECTSTRING = "192.168.74.128:2181,192.168.74.129:2181,192.168.74.130:2181,192.168.74.131:2181";

    private final static String MASTER_PATH = "/curator_master_path";
    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, MASTER_PATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("获得leader成功");
                TimeUnit.SECONDS.sleep(2);
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }
}
