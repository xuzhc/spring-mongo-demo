package com.xuzhch.springmongodemo.demo.dao;

import com.xuzhch.springmongodemo.demo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {
    List<Member> list();
}
