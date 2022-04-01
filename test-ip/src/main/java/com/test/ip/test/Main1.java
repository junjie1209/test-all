package com.test.ip.test;

import com.test.ip.ipvalid.IpUtils;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/3/24
 */
public class Main1 {
    public static void main(String[] args) {
        String address = "10.0.0.255/33";
        String address1 = "any1";

        /*boolean inet4Ip = IpUtils.isInet4Ip(address);
        System.out.println(inet4Ip);*/

        boolean inet4IpOrNetworkMask = IpUtils.isInet4IpOrNetworkMask(address1);
        System.out.println(inet4IpOrNetworkMask);

       /* boolean inet4IpOrNetworkMask = IpUtils.isInet4IpOrNetworkMask(address);
        System.out.println(inet4IpOrNetworkMask);*/
    }
}
