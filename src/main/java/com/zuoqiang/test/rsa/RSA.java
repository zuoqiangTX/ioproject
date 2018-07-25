package com.zuoqiang.test.rsa;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * RSA测试类
 *
 * @author zuoqiang
 */
public class RSA {
    private static Logger logger = LoggerFactory.getLogger(RSA.class);

    private static final String ALGORITHM = "SHA256WithRSA";
    private static final String RSA = "RSA";

    /**
     * 签名
     *
     * @param src
     * @param privateKey
     * @return
     */
    public static String encrypt(String src, String privateKey) {

        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes("UTF-8")));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey pk = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initSign(pk);
            signature.update(src.getBytes("UTF-8"));
            byte[] enSign = Base64.encodeBase64(signature.sign());

            return new String(enSign, "UTF-8");
        } catch (Exception e) {
            logger.error("RSA签名失败...", e);
        }

        return "";
    }


    /**
     * 解签
     *
     * @param src
     * @param publicKey
     * @return
     */
    public static boolean decrypt(String src, String signedSrc, String publicKey) {

        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey.getBytes("UTF-8")));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey pk = keyFactory.generatePublic(x509EncodedKeySpec);

            byte[] signed = Base64.decodeBase64(signedSrc);
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(pk);
            signature.update(src.getBytes("UTF-8"));

            return signature.verify(signed);
        } catch (Exception e) {
            logger.error("RSA解签失败...", e);
        }

        return false;

    }


    public static void main(String[] args) {
        // 原始可用， keytool 生成
        //String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsN5tTl+0xVvG/rsAomfIpBfrGG8ZqYYxRYw6313LuYHOfCRXIK3HJsKvHgR4pkCdHu9OjFpm1zYBP41xphfa9YruZPELV45sOx033iNhQl+Pio+ToCKbd0KqxLnczS8UYJRMkYtMecOvVX6hk2Sa3Xa7UKydZ0kJeKMwJZbbACwIDAQAB";
        //String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKw3m1OX7TFW8b+uwCiZ8ikF+sYbxmphjFFjDrfXcu5gc58JFcgrccmwq8eBHimQJ0e706MWmbXNgE/jXGmF9r1iu5k8QtXjmw7HTfeI2FCX4+Kj5OgIpt3QqrEudzNLxRglEyRi0x5w69VfqGTZJrddrtQrJ1nSQl4ozAlltsALAgMBAAECgYA1v60kq9gFfCM7NekXQuTHVOxthCGq9gL7K7VeP0nJiCTNz+ISdSywTAlw3KVzR2BbDQ4Can1Stm6DgGxJKtxUv9m99oQz8UjUm7nVZ1h16tZKnU/UM3dTr5Zv6i5cANvdtWWYsQJBDa9td+xfCWT7NLxszDzOSPyDfi3B13bEsQJBAOJrX9I9z1UCLyPlBWqGld+5qZc743RwHpF2L8l7S+7j7hLdpMkKsLy129MW1sj8jlpYAVCFHB42Sdi1yGnPMFcCQQDCt27A7YpBcIYRUabu6SfkSxiwNlp4yPF5ZwabGWn2oFmXNiXt5yRJWBqMkhGO2azvHjuNvbgpT6JVoNrDk01tAkBJhbZizcFiiEgIbtjYY4WNVXuVaVQNvk6ey3LvCQN6OjdXlvNoePk0/hS4seTi9OfAvG0I2YBs6xIFtdmzSh0XAkBO9bOj6K+3+blkLIhVOZdDrHBGhMQLVvEVGgJDwvqFWGD8QKkA4EqGs4OQnWshUoQeM1RTFiwoFHLv5lrQFFvBAkEAkcNokvtZbh/WncBhspGUAHgJJNNBY/PETxL3U7MhB1nk3tZZYhYH480Ym9ozbVv+EQykpTrxdZUyU5mQrwyElQ==";

        // openssl生成  privateKey 为pkcs8格式， publicKey为X509格式
        //String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALjWzTEHi3aaD+ZRwLpAsnZ+Q1JfarKd2xfHfQsdipROVPGopeX1RNph0WPKWj4Kow7uk06rMabIFWoNdgCXaNAfp8ojv6itmZwPjHI0pqjsnkW1bnmOtRAkYEUSObsSoL8iSooWmRgdvQV1BVUk+gfnoqgCkgJ3MaSSnKlR7asRAgMBAAECgYAmZYiufLC97HcNE0KD7likQMpfXC7f9+bBY9FX88UL1dHTeYMs5+xrqRJENo7UgIUq3uh+2RUxXjMEmtM+gDVnW8HKM+JAyeHDv4a6AyDRCCjEhe0Bo5nXrsb/vMErInMjHUQums0av89Ft7rvL9SgWAUfgs6K/6nd4aw3rgF+IQJBAO9gfQZ/Y/p4knF18DOsjoBEffAY51tqUHcvhfK+6RpbShmrH+RGWEfN4ctQZUIE8AiTECp9CEn8wJ/y0Z5yhWUCQQDFrMTwyhcdqcA4KQ6zCXHE4m+t1oAsrsNGeB4WszJ64UbX0gAfX8UyTMFfmyh5BKjqILa4ynDwmnEGhM9/jzo9AkEAqaR61lj/lKlQN0J6yVQ35SUlDzvG8l0ky2KNjhvH/RrHJ+vDfTZxJ+n7Y+gE6sWvCpSWd1YGwtBx/qjJ731/lQJBAL3JlQRCY1M9ianh8ZT/ulV3uhXDXiiIWkAsT2coAY1tASFxJGD7CE+01ew/Aeq/CJZRdl2RMEIxZdcGaFpmX1kCQGcePLsotDw0rAvRoehPieVN5PjYkDkSATefDhvHS1ePESN3M/6HlT3MCZ+Vmht1ghQ8gbHD2iIINL1ESdgDP/0=";
        //String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC41s0xB4t2mg/mUcC6QLJ2fkNSX2qyndsXx30LHYqUTlTxqKXl9UTaYdFjylo+CqMO7pNOqzGmyBVqDXYAl2jQH6fKI7+orZmcD4xyNKao7J5FtW55jrUQJGBFEjm7EqC/IkqKFpkYHb0FdQVVJPoH56KoApICdzGkkpypUe2rEQIDAQAB";


//    	String privateKey = "MIICXAIBAAKBgQDMbJmRlMSJFQ8pTT/KkFp8WHrwVT7t56npunirgIOHo2k1fJrE\n" +
//				"ODNaS7uUvmTj5eZp9RkbLxQuUZSwXs+2b0ef4YcINWAuSuqhB//bg1wCpFzMPyPE\n" +
//				"5btln3y10yQSeCIvIkKq9o3wYuuUATki6+TbZjj72Q7L30iutiPKecVHfwIDAQAB\n" +
//				"AoGAKpCaUVPWsGxwIbtyAu2XybhqsMwvND4+hOpM4fj6ai2+ZGKznxPMrYqDXm9e\n" +
//				"do2SMpHUTnK5EwPtn+O+AFCfau22oK+NjlV51VmodzblhJ/Uu7xQfWQ7cGZanQLS\n" +
//				"4QzZN+/6RwvyMs+BueqoqXqu/EKeKGtPq4GUK84wJnBxEVECQQDnkfWc6dg0G18C\n" +
//				"bHt9FAu/2Z4qZE3T2q0sbz39cCKbypfNshff2tbki4JQGgfqk/oC2G33y6RqOlhU\n" +
//				"b/rfVhidAkEA4f2B1uQo7tj1ylW2jtc5sNJbDE04xdit6P847I4Bpn7lwzUoAIB4\n" +
//				"gA25IxjMrOMKjiRtb1l2mfHCNTROQpvfywJAXmU+RM+CJYN+0EMed5cjTvpLoiYv\n" +
//				"H4wFiZZzI4uBYBZOmTfeyY6wiIth/HsP/QyDHoPRF/C4PeE4vnrDPiUfdQJBALFf\n" +
//				"db8iSPZel1v9tlGan48nK4sWOQ8gKC+N0jHoq2bwhomWeRUbwHhRTRQ87LhtYuV5\n" +
//				"teMFhs8BL3ru2NXy3c0CQCxwM9yhItEVPB7Ey7tWApEFkrweJxSLP/WnQSt5hglH\n" +
//				"j8xDOVOn3VPpgYfu3VVQn8PZXSCFqHrXofDZksUtwZk=";
//    	

        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJQVS7RDaDFw0fj/\n" +
                "zgfPAvv2fBQrGCV975svjVnJAtivmXYqx4CBv7LWTjV5swKr2oq3EVpBTAlsGqJO\n" +
                "L+5gxa13JcPh+0FlJ957xSUY2L1B1tyEvgLugTZSw8rSsiblWkxtld0ncozx3nLS\n" +
                "VanWBWW84RoBdMMN38IEiTGM6r8/AgMBAAECgYAKQh9ocXL6WdJx6Waga9rNEZou\n" +
                "231ku4Cpw3WLTr1/vUpoQTZo1JQvE2F/mtqnyLfu/BebpQJbdL2zmK8BWHrZGnn5\n" +
                "LujgfG7wL66+qxtfbRtP3WFukzJM/E7IbT9dEUDLBfggQq74q+B2qJR3T2tjVXaV\n" +
                "mH7hwxeHubTy8sFBgQJBAMN6hM1oOAcnGlfG0G7PcZ/ydFRb9PnRgYCnnHtqy0/v\n" +
                "v6K8M57Bq+51rWPbscGyrDfzbNB5z1nvvBtD8+XAv7ECQQDB7kEPfv8kvt7VVim0\n" +
                "hagUxMZuHFyjNpNKyvD9S7z8nQ7Hg9PHb/TtPcYMowaFZb3BuAxs8yfrWaXBwtXq\n" +
                "l5nvAkA5YnMv9aNU/jaug+/WR9GcWcmqpiAhYk55HTtDw9MRkVkZhdRFi6dUZnlT\n" +
                "MkBZk4EZH73W6ZtkVItNGcK0tMPRAkBYSm1qbaohg5iVzfWMz3D3i6K/jMAMOpOS\n" +
                "cJSI7UtsQuDwqq7nVKxQulgJdstIPkrHAFD/5TAecSEK9vZHKKAXAkEApH/EBvn/\n" +
                "P3SjzZLgf5BHDSdXd0i3C6miao4EacsWQJLGnR9RUP+bOe4UKhjD3zBs8aGOcy8l\n" +
                "VK+BwIg8Ek/8zA==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUFUu0Q2gxcNH4/84HzwL79nwUKxglfe+bL41ZyQLYr5l2KseAgb+y1k41ebMCq9qKtxFaQUwJbBqiTi/uYMWtdyXD4ftBZSfee8UlGNi9QdbchL4C7oE2UsPK0rIm5VpMbZXdJ3KM8d5y0lWp1gVlvOEaAXTDDd/CBIkxjOq/PwIDAQAB";

        //String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMbJmRlMSJFQ8pTT/KkFp8WHrwVT7t56npunirgIOHo2k1fJrEODNaS7uUvmTj5eZp9RkbLxQuUZSwXs+2b0ef4YcINWAuSuqhB//bg1wCpFzMPyPE5btln3y10yQSeCIvIkKq9o3wYuuUATki6+TbZjj72Q7L30iutiPKecVHfwIDAQAB";
        String signedSrc = encrypt("123", privateKey);
        String signedSrc2 = encrypt("123", privateKey);
        System.out.println("signedSrc: " + signedSrc);
        System.out.println("signedSrc2: " + signedSrc2);
        System.out.println(signedSrc.equalsIgnoreCase(signedSrc2));

        System.out.println(decrypt("123", signedSrc, publicKey));


    }
}
