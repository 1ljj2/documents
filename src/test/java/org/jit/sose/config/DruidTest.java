package org.jit.sose.config;

import org.jit.sose.TestAppDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;

public class DruidTest extends TestAppDocument {
    @Autowired
    DataSource dataSource;

    /**
     * 测试druid数据源
     *
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception {
        System.out.println("数据源：" + dataSource.getClass());
        // 获取sql连接
        Connection connection = dataSource.getConnection();
        System.out.println("数据库连接：" + connection);
        connection.close();
    }
}
