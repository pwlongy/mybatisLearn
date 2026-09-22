package com.mybatis;

import com.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TestMybatis {
    @Test
    public void testSqlSessionUtil() {
        // sqlsession 需要关闭，可以使用 try 包裹，就不许手动关闭
        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            int count = sqlSession.insert("insertUser");
            System.out.println(count == 1 ? "添加成功" : "添加失败");
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSqlSessionUtil02() {
        // 添加自定义数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小红");
        map.put("age", "19");
        map.put("address", "北京故宫");
        map.put("car", "卡迪拉克");
        map.put("animal", "小狗");

        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            sqlSession.insert("insertUser01", map);
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSqlSessionUtil03() {
        User user = new User();
        user.setName("小静");
        user.setAge(19);
        user.setAddress("北京故宫");
        user.setCar("保时捷");
        user.setAnimal("小猫");

        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            sqlSession.insert("insertUser02", user);
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteSqlSession() {
        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            int count = sqlSession.delete("deleteById", 9);
            System.out.println(count == 1 ? "删除成功" : "删除失败");
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestUpdateSqlSession() {
        User user = new User("小红", 18, "江西南昌", "奥迪", "小猫");
        user.setId(1);
        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            sqlSession.update("updateById", user);
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSelectSqlSession() {
        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            User user = (User) sqlSession.selectOne("slectById", 1);
            System.out.println(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSelectAllSqlSession() {
        try(SqlSession sqlSession = SqlSessionUtil.openSession()) {
            List<User> userList = sqlSession.selectList("selectAll");
            System.out.println(userList);
            sqlSession.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
