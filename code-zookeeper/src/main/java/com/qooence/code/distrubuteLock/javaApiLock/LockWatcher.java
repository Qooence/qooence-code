package com.qooence.code.distrubuteLock.javaApiLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class LockWatcher implements Watcher {

    public LockWatcher(CountDownLatch latch) {
        this.latch = latch;
    }
    CountDownLatch latch;
    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDeleted){
            latch.countDown();
        }
    }
}
