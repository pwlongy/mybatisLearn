package com.mybatis.user.mapper;

import com.mybatis.user.entity.UserEntity;

import java.util.List;

/**
 *
 */
public interface userMapper {
    List<UserEntity> selectAllByTableName(String tableName);
    List<UserEntity> selectLikeTabeDate(String name);
    void insertUserData(UserEntity userEntity);
}