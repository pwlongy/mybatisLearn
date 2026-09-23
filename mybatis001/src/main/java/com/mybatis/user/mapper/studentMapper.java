package com.mybatis.user.mapper;

import com.mybatis.user.entity.Student;

import java.util.List;

/**
 *
 */
public interface studentMapper {
    List<Student> selectAllByTableName(String tableName);
    List<Student> selectLikeTabeDate(String name);
    void insertStudent(Student student);
}
