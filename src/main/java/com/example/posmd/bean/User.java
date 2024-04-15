package com.example.posmd.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * FileName:    User
 * Author:      Yuan-Programmer
 * Date:        2021/12/10 21:42
 * Description:
 */
@Entity(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String nickname;
    private String password;
}
