package com.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *
 */
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    // 因为sqlSessionFactory只需要一个，所以使用 static 去创建
    static {
        try(InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 获取 sqlsession 对象
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }
}
