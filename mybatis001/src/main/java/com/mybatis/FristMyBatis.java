package com.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 *
 */
public class FristMyBatis {
    public static void main(String[] args) {
        overUserMybatisReadfile();
    }
    // 使用 Mybatis 进行数据添加
    static void useMybatis() {
        //      使用 mybatis 步骤
        /**
         *  1. 创建 SqlSeesionFactoryBuilder 对象
         *  SqlSeesionFactoryBuilder 是一个建造对象， 这个建造器是用来创建 SqlSeesionFactory 对象
         *  SqlSeesionFactory 对象是一个工厂对象，通过这个工厂可以生产 sqlsSession 对象
         *  SqlSession 对象相当于是和数据库的一次会话，用它来执行 SQL 语句
         *  通过 SqlSessionFactoryBuilder 去创建 SqlSessionFactory对象， 通过 SqlSessionFactory对象 去创建sqlsSession 对象
         *  强调： 对于 sqlsSessionFactory 对象来说，整个应用保持只有一个
         *  SqlSeesionFactoryBuilder 只要将 SqlSeesionFactory 创建成功， SqlSeesionFactoryBuilder 对象就没用了
         *
         * */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        /**
         * 2. 创建输入流： 这个输入流指向 Mybatis-config.xml 配置文件
         * 从类路径当中查找这个 mybatis-config.xml 文件
         * */
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        /**
         * 3. 通过 SqlSessionFactoryBuilder 的build 方法来创建一个 SqlSessionFactory 对象
         * */
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        /**
         * 4. 通过 sqlSessionFactory 对象来获取 sqlSession 对象（通过他来执行sql）
         * */
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /**
         * 5. 执行 SQL 语句（执行 insert 语句， 因此调用 insert 方法）
         * 参数非常重要， 是 sqlMapper.xml 文件中配置的sql的id
         * 返回值标识影响力数据库中的几条记录
         * */
        int count = sqlSession.insert("insertUser");

        if(count > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        /**
         * 6. mybatis 默认使用的事务管理器是JDBC
         * mybatis 管理事务的时候，委托给JDBC 管理事务，因此叫做采用JDBC的事务管理机制
         * JDBC的管理机制中： conn.setAutoCommit(false);  手动提交事务
         * 因此 mybatis 采用了JDBC的事务管理机制，因此在底层他偷偷执行了 conn.setAutoCommit(false);
         * 因此在使用mybatis时候，需要手动提交
         *
         * */
        sqlSession.commit();
        // 关闭 sqlSession 对象
        sqlSession.close();
    }

    // 优化 Mybatis 数据
    static void OptimizeUsMybatis() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.insert("insertUser");
        if(count > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    // 使用Mybatis 自带文件读取功能
    /**
     * 直接使用 mybatis 中提供的 org.apache.ibatis.io.Resources 类来读取文件, 加载核心配置文件
     * Resources 默认就是从类的根路径下开始加载。
     * */
    static void useMybatisReadFile() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml");){
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            int count = sqlSession.insert("insertUser");
            System.out.println(count);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  InputStream 会自动关闭
    //  sqlSession 会自动关闭
    /**
     * InputStream 实现了 Closeable，而 Closeable 继承自 AutoCloseable → 可自动关闭。
     * SqlSession 接口也继承了 Closeable（MyBatis 源码中 public interface SqlSession extends Closeable）→ 同样可自动关闭。
     * */
    static void overUserMybatisReadfile() {
        try(InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
                int count =  sqlSession.insert("insertUser");
                System.out.println(count == 1 ? "添加成功" : "添加失败");
                sqlSession.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
