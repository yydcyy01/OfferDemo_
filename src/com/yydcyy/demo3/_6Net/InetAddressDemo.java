package com.yydcyy.demo3._6Net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author YYDCYY
 * @create 2019-11-19
 * InetAddress
 * 没有共有构造函数, 只能通过静态方法创建实例
 */
public class InetAddressDemo {
    public static void main(String[] args) {
    }

    public void testInet(String host, byte [] address) throws UnknownHostException {

        InetAddress.getByName(host);
        InetAddress.getByAddress(address);

    }
}
