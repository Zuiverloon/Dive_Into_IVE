package org.zjy.diveintoive.Utils;

import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Component
public class NetworkUtil {

    NetworkUtil(){
//        System.out.println("networkutil init");
//        this.ip = queryIP();
//        System.out.println("ip="+this.ip);
    }

    private String queryIP(){
        Enumeration<NetworkInterface> all = null;
        try {
            all = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (all.hasMoreElements()){
                NetworkInterface networkInterface = (NetworkInterface) all.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp())continue;
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    ip = addresses.nextElement();
                    if (ip!=null && ip instanceof Inet4Address){
                        //System.out.println("possible ip="+ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "0.0.0.0";

    }
    private String ip;

    public String getIP(){
        if (ip == null){
            ip = queryIP();
        }
        return ip;
    }

}
