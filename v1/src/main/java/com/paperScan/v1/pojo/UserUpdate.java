package com.paperScan.v1.pojo;


import lombok.Data;

@Data
public class UserUpdate {
    private Long userId;

    private String userName;

    private String password;

    private Integer age;

    public UserUpdate(Long userId, String userName, String password, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public UserUpdate() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}