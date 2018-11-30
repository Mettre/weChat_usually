package com.mettre.usually.dto;

import com.mettre.usually.enum_.GenderEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String userId;

    private String userName;

    private String signature;

    private GenderEnum gender;

    private String headAvatar;

    private String password;

    private String phone;

    private String city;

    private Integer age;

    private String backgroundWall;

    private Date creationTime;

    private Date updateTime;

    public UserDto() {
    }

    public UserDto(String userId, String userName, String signature, GenderEnum gender, String headAvatar, String password, String phone, String city, Integer age, String backgroundWall, Date creationTime, Date updateTime) {
        this.userId = userId;
        this.userName = userName;
        this.signature = signature;
        this.gender = gender;
        this.headAvatar = headAvatar;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.age = age;
        this.backgroundWall = backgroundWall;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }


    //个人信息没有密码
    public UserDto(UserDto user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.signature = user.getSignature();
        this.gender = user.getGender();
        this.headAvatar = user.getHeadAvatar();
        this.phone = user.getPhone();
        this.city = user.getCity();
        this.age = user.getAge();
        this.backgroundWall = user.getBackgroundWall();
        this.creationTime = user.creationTime;
    }

}