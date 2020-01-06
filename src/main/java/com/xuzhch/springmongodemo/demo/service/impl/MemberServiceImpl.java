package com.xuzhch.springmongodemo.demo.service.impl;

import com.xuzhch.springmongodemo.demo.dao.MemberDao;
import com.xuzhch.springmongodemo.demo.entity.Member;
import com.xuzhch.springmongodemo.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhch
 * @className MemberServiceImpl
 * @description 用户信息
 * @date 2020/1/6/006
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Member> list() {
        List<Member> list = memberDao.list();
        return list;
    }
}
