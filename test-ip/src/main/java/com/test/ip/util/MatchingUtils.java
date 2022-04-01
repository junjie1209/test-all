package com.test.ip.util;

/**
 * 匹配工具类
 *
 * @author: Fushuai Lu
 * @date: 2022/03/24
 */
public class MatchingUtils {

    public static final String HAS_SUBNET = "/";

    public static final String TYPE_D_IP_SUBNET = "255.255.255.255";

    public static final Integer IPV4_BINARY_LEN = 32;

    private MatchingUtils() {
    }

    /**
     * 判断网络地址1是否涵盖网络地址2
     *
     * @param sonAddress 网络地址1
     * @param dadAddress 网络地址2
     * @return 如果涵盖返回true，否则返回false
     */
    public static boolean isInet4NetworkBelongNetwork(String sonAddress, String dadAddress) {
        // 地址1与子网掩码后地址
        String address1Ip = getAddrIp(dadAddress);
        // 地址2与子网掩码后地址
        String address2Ip = getAddrIp(sonAddress);

        int ip1SubnetLength = Integer.parseInt(dadAddress.split(HAS_SUBNET)[1]);
        boolean equals = address1Ip.substring(0, ip1SubnetLength).equals(address2Ip.substring(0, ip1SubnetLength));
        if (!sonAddress.contains(HAS_SUBNET)) {
            return equals;
        } else {
            int ip2SubnetLength = Integer.parseInt(sonAddress.split(HAS_SUBNET)[1]);
            return ip2SubnetLength >= ip1SubnetLength && equals;
        }
    }

    /**
     * 获取ip的地址位
     *
     * @param address 只支持ip地址和ip/掩码长度形式，例如：192.168.127.1/24
     * @return ip的地址位
     */
    private static String getAddrIp(String address) {
        StringBuilder addrIp = new StringBuilder();
        String binaryIp;
        String binarySubnetMask;
        if (address.contains(HAS_SUBNET)) {
            String[] split = address.split(HAS_SUBNET);
            binaryIp = getBinaryIp(split[0]);
            binarySubnetMask = getSubnetByLength(Integer.valueOf(split[1]));
        } else {
            binaryIp = getBinaryIp(address);
            binarySubnetMask = getBinaryIp(TYPE_D_IP_SUBNET);
        }

        for (int i = 0; i < IPV4_BINARY_LEN; i++) {
            byte ipByte = Byte.parseByte(String.valueOf(binaryIp.charAt(i)));
            byte subnetMaskByte = Byte.parseByte(String.valueOf(binarySubnetMask.charAt(i)));
            addrIp.append(ipByte & subnetMaskByte);
        }
        return addrIp.toString();
    }

    /**
     * 根据掩码长度获取掩码二进制表示
     *
     * @param subnetLength 掩码长度
     * @return 掩码二进制表示
     */
    private static String getSubnetByLength(Integer subnetLength) {
        StringBuilder subnet = new StringBuilder();
        for (int i = 0; i < IPV4_BINARY_LEN; i++) {
            if (i < subnetLength) {
                subnet.append("1");
            } else {
                subnet.append("0");
            }
        }
        return subnet.toString();
    }

    /**
     * 获取ip的二进制字符串
     *
     * @param ip ip
     * @return ip的二进制字符串
     */
    private static String getBinaryIp(String ip) {
        String[] data = ip.split("\\.");
        StringBuilder binaryIp = new StringBuilder();
        for (String ipStr : data) {
            long signIp = Long.parseLong(ipStr);
            String binary = Long.toBinaryString(signIp);
            long binaryInt = Long.parseLong(binary);
            binaryIp.append(String.format("%08d", binaryInt));
        }
        return binaryIp.toString();
    }

}
