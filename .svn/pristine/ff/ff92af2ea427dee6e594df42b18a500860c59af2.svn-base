package org.jit.sose.util;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author 小红
 * @Date 2020/7/19 4:41
 */
public class EncryptionUtilTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
        Map<Integer, String> keyMap = EncryptionUtil.getRsaKeyPair();
        System.out.println(keyMap);
    }

    /**
     * 获取指定长度AES对称加密密钥
     */
    @Test
    public void getAesKey() {
        Integer n =null;
        String getAesKey = EncryptionUtil.getAesKey(n);
        System.out.println("getAesKey:  "+getAesKey);
    }



}
