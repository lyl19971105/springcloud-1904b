package com.jk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String  userName;
    private String  userPwd;
    private String userTime;
}
