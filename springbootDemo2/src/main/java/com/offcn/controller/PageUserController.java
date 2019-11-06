package com.offcn.controller;

import com.offcn.dao.UserDao;
import com.offcn.entity.MUser;
import com.offcn.entity.User;
import com.offcn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageUserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/show/user/list")
    public String showUserList(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("userList",users);
        return "user";
    }

    @Value("${page.rows}")//获取application.yml的rows值
    private Integer rows;
    @RequestMapping("/page/code")
    @ResponseBody
    public Map getPage(){
        Map map=new HashMap();
          map.put("rows",rows);
          return map;
    }


    @RequestMapping("/user/list2")
    @ResponseBody
    public List<MUser> getUserList(){
       return userMapper.getUserList();
    }

}
