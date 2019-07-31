package com.qooence.code.MulticastSocketDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 服务端 组播（UDP/IP）
 */
public class MulticastReceive {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("224.6.7.8");
            DatagramPacket recvPack = new DatagramPacket(new byte[1024],1024);
            MulticastSocket server = new MulticastSocket(8888);
            server.joinGroup(inetAddress);
            System.out.println("----------------------");
            System.out.println("Server current start");
            System.out.println("----------------------");

            while (true){
                server.receive(recvPack);
                byte[] recvByte = Arrays.copyOfRange(recvPack.getData(),0,recvPack.getLength());
                System.out.println("Server receive msg:" + new String(recvByte));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
