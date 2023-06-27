package org.zjy.diveintoive.Utils;

import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Component
public class NetworkUtil {

    private String queryIP(){
        Enumeration<NetworkInterface> all = null;
        try {
            all = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (all.hasMoreElements()){
                NetworkInterface networkInterface = all.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp())continue;
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    ip = addresses.nextElement();
                    if (ip!=null && ip instanceof Inet4Address){
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "0.0.0.0";

    }
    private final String ip = "192.168.0.173";

    public String getIP(){
//        if (ip == null){
//            ip = queryIP();
//        }
        return ip;
    }

}
