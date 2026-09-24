package com.mybatis;

import com.mybatis.user.entity.Student;
import com.mybatis.user.entity.UserEntity;
import com.mybatis.user.mapper.studentMapper;
import com.mybatis.user.mapper.userMapper;
import com.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Test
    public void TestSlectUserEntitySqlSession() {
        try {
            userMapper userMapper = SqlSessionUtil.openSession().getMapper(userMapper.class);
            List<UserEntity> userEntityList = userMapper.selectAllByTableName("user");
            Iterator iterator = userEntityList.iterator();
            while (iterator.hasNext()) {
                Object next =  iterator.next();
                System.out.println(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSelectLikeUserEntitySqlsession() {
        try {
            userMapper mapper  = SqlSessionUtil.openSession().getMapper(userMapper.class);
            List<UserEntity> userEntityList = mapper.selectLikeTabeDate("小");
            Iterator iterator = userEntityList.iterator();
            while (iterator.hasNext()) {
                Object next =  iterator.next();
                System.out.println(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestInsertUserEntitySqlSession() {
        try {
            SqlSession sqlSession = SqlSessionUtil.openSession();
            userMapper map = sqlSession.getMapper(userMapper.class);
            UserEntity userEntity = new UserEntity("longyu", 18, "江西吉安", "卡迪拉克", "小狗");
            map.insertUserData(userEntity);
            sqlSession.commit();
            System.out.println(userEntity.getId());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestInsertStudentEntitySqlSession() {
        try {
            SqlSession sqlSession = SqlSessionUtil.openSession();
            studentMapper mapper = sqlSession.getMapper(studentMapper.class);
            Student student = new Student("龙玉", 18, "女", "计算机科学以技术1702班", 100.0,"18214523695", getNowTimeDate());
            mapper.insertStudent(student);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSelectStudentEntitySqlSession() {
        try {
            SqlSession sqlSession = SqlSessionUtil.openSession();
            studentMapper mapper = sqlSession.getMapper(studentMapper.class);
            List<Student> students = mapper.selectStudentName("张");
            Iterator iterator = students.iterator();
            while (iterator.hasNext()) {
                Object next =  iterator.next();
                System.out.println(next);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestselectDynamicData() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "小");
        params.put("age", 18);
        params.put("createTime", "2026-09-03");
        try {
            studentMapper mapper = SqlSessionUtil.openSession().getMapper(studentMapper.class);
            mapper.selectDynamicData(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNowTimeDate() {
        LocalDateTime now = LocalDateTime.now();
        // 1. 自定义格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String result = now.format(fmt);
        return result;
    }
}
