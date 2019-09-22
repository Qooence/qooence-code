package com.qooence.code.order;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SysHelloImpl extends UnicastRemoteObject implements ISayHello {

    protected SysHelloImpl() throws RemoteException {
    }

    @Override
    public String sysHello(String name) throws RemoteException {
        return "HELLO Qooence -> " + name;
    }
}
