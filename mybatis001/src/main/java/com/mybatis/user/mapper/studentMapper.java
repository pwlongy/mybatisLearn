package com.mybatis.user.mapper;

import com.mybatis.user.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface studentMapper {
    List<Student> selectAllByTableName(String tableName);
    List<Student> selectLikeTabeDate(String name);
    void insertStudent(Student student);
    List<Student> selectStudentName(@Param("name") String name);
    List<Student> selectDynamicData(Map map);


}
