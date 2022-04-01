package com.test.ip.ipvalid;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/3/24
 */
public class IpUtils {
    /**
     * 带有掩码的ipv4正则表达式
     */
    private static final Pattern inet4NetworkMaskPattern = Pattern
            .compile("^(?:(?:[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(?:[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\/([1-9]|[1-2]\\d|3[0-2])$");

    private static final Pattern inet4IpOrNetworkMaskPattern = Pattern
            .compile("^((?:(?:[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(?:[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\/([1-9]|[1-2]\\d|3[0-2]))?)|any$");


    public static boolean isInet4IpOrNetworkMask(String address){
        Matcher matcher = inet4IpOrNetworkMaskPattern.matcher(address);
        return matcher.matches();
    }

    public static boolean isInet4Ip(String address) {
        Objects.requireNonNull(address);
        return InetAddressValidator.getInstance().isValidInet4Address(address);
    }

    public static boolean isInet4NetworkMask(String address) {
        Matcher matcher = inet4NetworkMaskPattern.matcher(address);
        return matcher.matches();
    }
}
