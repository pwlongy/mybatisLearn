package com.mybatis.user.entity;

/**
 *
 */
public class UserEntity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int age;
    private String address;
    private String car;
    private String animal;

    public UserEntity(String name, int age, String address, String car, String animal) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.car = car;
        this.animal = animal;
    }

    public UserEntity() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnimal() {
        return animal;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", car='" + car + '\'' +
                ", animal='" + animal + '\'' +
                '}';
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
