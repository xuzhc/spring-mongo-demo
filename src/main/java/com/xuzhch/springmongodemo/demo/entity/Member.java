package com.xuzhch.springmongodemo.demo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuzhch
 * @className Member
 * @description 用户信息
 * @date 2020/1/6/006
 */
@Data
@Document("member")
public class Member implements Serializable {
    private Long id;
    private String account;
    private String nickName;
    private Integer sex;
    private String ip;
    private Date createTime;

}
