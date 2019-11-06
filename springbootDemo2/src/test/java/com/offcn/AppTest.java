package com.offcn;

import com.offcn.entity.MUser;
import com.offcn.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)//让spring跑起来
@SpringBootTest(classes = HelloApplication.class)//主启动类
public class AppTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public  void test01(){
        List<MUser> userList = userMapper.getUserList();
        for (MUser user: userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testRedisTemplate(){
        // 存d到redis数据库
        redisTemplate.opsForValue().set("hello","0708java");
        // 取
        String str =(String) redisTemplate.opsForValue().get("hello");
        System.out.println(str);
        MUser user = new MUser();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("123");
        user.setName("超人");
        redisTemplate.opsForValue().set("user",user);
        MUser user1 = (MUser)redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }
}
