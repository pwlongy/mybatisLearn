package com.mybatis.user.entity;

import java.util.Date;

/**
 *
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String className;
    private Double score;
    private String phone;
    private Date createTime;

    public Student(String name, int age, String gender, String className, Double score, String phone, Date createTime) {
        this.gender = gender;
        this.name = name;
        this.age = age;
        this.className = className;
        this.score = score;
        this.phone = phone;
        this.createTime = createTime;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", className='" + className + '\'' +
                ", score=" + score +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
