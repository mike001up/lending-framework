package com.pig4cloud.pig.common.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 * IP 地址匹配工具类
 * 支持格式：精确IP、通配符(*)、CIDR、IP范围
 */
public class IpMatcherUtil {

    public interface IpRule {
        boolean matches(InetAddress ip);
    }

    public static IpRule parseRule(String pattern) {
        if (pattern.contains("/")) {
            return new CidrRule(pattern);
        } else if (pattern.contains("-")) {
            return new RangeRule(pattern);
        } else if (pattern.contains("*") || pattern.contains("?")) {
            return new WildcardRule(pattern);
        } else {
            return new SingleIpRule(pattern);
        }
    }

    // ============ 内部实现 ============

    static class SingleIpRule implements IpRule {
        private final InetAddress address;
        SingleIpRule(String ip) {
            try {
                this.address = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException("Invalid IP: " + ip, e);
            }
        }
        @Override
        public boolean matches(InetAddress ip) {
            return address.equals(ip);
        }
    }

    static class WildcardRule implements IpRule {
        private final Pattern pattern;
        WildcardRule(String wildcard) {
            String regex = wildcard
                    .replace(".", "\\.")
                    .replace("*", "\\d{1,3}")
                    .replace("?", "\\d{1,3}");
            this.pattern = Pattern.compile("^" + regex + "$");
        }
        @Override
        public boolean matches(InetAddress ip) {
            String addr = ip.getHostAddress();
            return !addr.contains(":") && pattern.matcher(addr).matches();
        }
    }

    static class CidrRule implements IpRule {
        private final byte[] networkBytes;
        private final int prefixLength;
        CidrRule(String cidr) {
            String[] parts = cidr.split("/");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid CIDR: " + cidr);
            }
            try {
                InetAddress addr = InetAddress.getByName(parts[0]);
                this.networkBytes = addr.getAddress();
                this.prefixLength = Integer.parseInt(parts[1]);
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException("Invalid CIDR IP: " + cidr, e);
            }
        }
        @Override
        public boolean matches(InetAddress ip) {
            byte[] ipBytes = ip.getAddress();
            if (ipBytes.length != networkBytes.length) return false;
            int fullBytes = prefixLength / 8;
            for (int i = 0; i < fullBytes; i++) {
                if (ipBytes[i] != networkBytes[i]) return false;
            }
            int remainingBits = prefixLength % 8;
            if (remainingBits > 0) {
                int mask = 0xFF << (8 - remainingBits);
                if ((ipBytes[fullBytes] & mask) != (networkBytes[fullBytes] & mask)) {
                    return false;
                }
            }
            return true;
        }
    }

    static class RangeRule implements IpRule {
        private final long start;
        private final long end;
        RangeRule(String range) {
            String[] parts = range.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid range: " + range);
            }
            try {
                this.start = ipToLong(InetAddress.getByName(parts[0].trim()));
                this.end = ipToLong(InetAddress.getByName(parts[1].trim()));
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException("Invalid range IP: " + range, e);
            }
        }
        private long ipToLong(InetAddress ip) {
            byte[] bytes = ip.getAddress();
            long result = 0;
            for (byte b : bytes) {
                result = (result << 8) | (b & 0xFF);
            }
            return result;
        }
        @Override
        public boolean matches(InetAddress ip) {
            long val = ipToLong(ip);
            return val >= start && val <= end;
        }
    }
}