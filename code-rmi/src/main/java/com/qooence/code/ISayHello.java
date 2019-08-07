package com.qooence.code;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISayHello extends Remote {
    /**
     *
     * @param name 名称
     * @return
     * @throws RemoteException
     */
    String sysHello(String name) throws RemoteException;
}
