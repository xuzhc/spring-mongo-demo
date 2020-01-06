package com.xuzhch.springmongodemo.demo.service;

import com.xuzhch.springmongodemo.demo.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    List<Member> list();
}
