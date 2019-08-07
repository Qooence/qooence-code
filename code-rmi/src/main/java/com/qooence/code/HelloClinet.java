package com.qooence.code;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClinet {

    public static void main(String[] args) {
        try {

            ISayHello hello = (ISayHello) Naming.lookup("rmi://localhost:8888/sayHello");

            String returnStr = hello.sysHello("RMI");

            System.out.println("Return Result: " + returnStr);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
