package org.jit.sose.util;

import org.jit.sose.TestAppDocument;
import org.junit.Test;

public class MD5UtilTest extends TestAppDocument {

    @Test
    public void test() {
        String password = MD5Util.md5("123456","徐斌书记");
        System.out.println(password);
    }

}
