package com.xuzhch.springmongodemo;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xuzhch.springmongodemo.demo.entity.Member;
import com.xuzhch.springmongodemo.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class SpringMongoDemoApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MemberService memberService;

    @Test
    void aggTest1() {
//        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("sex").count().as("count"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("ip","sex").count().as("count"),
                Aggregation.project("count").and("ip").as("ip").andInclude("ip").and("sex").as("sex").andInclude("sex")

        );

        AggregationResults<Object> aggregate = mongoTemplate.aggregate(aggregation, Member.class, Object.class);
        log.info("{\"结果信息\":{}}", JSON.toJSONString(aggregate.getMappedResults()));
    }

    /**
     * 更新
     *
     * @return
     * @date 2020/1/6/006 21:28
     * @author xuzhch
     **/
    @Test
    void update() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1197773918820175873L));
        Update update = new Update();
        update.set("nickName", "这是修改后的_");
        UpdateResult upsert = mongoTemplate.upsert(query, update, Member.class);
        log.info("{\"结果信息\":{}}", JSON.toJSONString(upsert));
    }

    /**
     * 删除
     *
     * @return
     * @date 2020/1/6/006 21:13
     * @author xuzhch
     **/
    @Test
    void delete() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1197791036231974914L));
        DeleteResult remove = mongoTemplate.remove(query, Member.class);
        log.info("{\"结果信息\":{}}", JSON.toJSONString(remove));
    }


    /**
     * 大于查询
     *
     * @return
     * @date 2020/1/6/006 21:09
     * @author xuzhch
     **/
    @Test
    void query2() throws ParseException {
//        String date = "2019-11-20";
        String date = "2019-12-31";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        Query query = new Query();
        query.addCriteria(Criteria.where("createTime").gt(date1));
        List<Member> members = mongoTemplate.find(query, Member.class);
        log.info("{\"结果信息\":{}}", JSON.toJSONString(members));
    }


    /**
     * 匹配查询
     *
     * @return
     * @date 2020/1/6/006 21:09
     * @author xuzhch
     **/
    @Test
    void query1() {
        Query query = new Query();
        query.addCriteria(Criteria.where("nickName").is("游客"));
//        Field fields = query.fields();
//        fields.exclude("account");
//        query.addCriteria(Criteria.where("nickName").is("游客").andOperator(Criteria.where("account").is("17600433562")));
        List<Member> members = mongoTemplate.find(query, Member.class);
        for (Member member : members) {
            log.info("{\"结果信息\":{}}", JSON.toJSONString(member));

        }
    }

    @Test
    void list() {
        Query query = new Query();
        List<Member> members = mongoTemplate.find(query, Member.class);
        log.info("{\"结果信息\":{}}", JSON.toJSONString(members));

    }

    @Test
    void mysqlList() {
        List<Member> list = memberService.list();
        Collection<Member> insert = mongoTemplate.insert(list, Member.class);
        log.info("结果信息：{}", insert.size());

        mongoTemplate.save(new Object());
        mongoTemplate.insert(new Object());

    }


}
