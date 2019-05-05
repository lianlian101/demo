package com.mvn.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mvn.test.entity.User;
import com.mvn.test.entity.pojo.UserResult;
import com.mvn.test.service.UserService;
import com.mvn.test.util.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {
    
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(@RequestBody String data, HttpServletRequest request, Map<String, String> map){
        
        JsonResult jsonResult = new JsonResult();
        
        String key = request.getParameter("key");
        System.out.println("key is: " + key);
        System.out.println("json is: " + data);
        JSONObject parse = JSON.parseObject(data);
        System.out.println("解析后的数据为: " + parse);
        
        System.out.println(jsonResult);
        
        switch (key) {
        case "1": 
            request.setAttribute("jsonResult", jsonResult); 
            return "forward:/success.jsp?key=1";
        
        default: 
            jsonResult.setCode(0);
            jsonResult.setMsg("操作失败");
            request.setAttribute("jsonResult", jsonResult);
            return "forward:/fail.jsp?key=other";
        }
        
    }
    
    @RequestMapping("/index2")
    @ResponseBody
    public String index2(@RequestBody String data){
        
        System.out.println("json is: " + data);
        JSONObject parse = JSON.parseObject(data);
        System.out.println("解析后的数据为: " + parse);
        
        return "index2";
        
    }
    
    @RequestMapping("/index3")
    @ResponseBody
    public String index3(@RequestBody(required=false) String data){
        
        System.out.println("json is: " + data);
        System.out.println("json is: " + data.isEmpty());
        
        return "index3";
        
    }
    
    @RequestMapping("getUser")
    @ResponseBody
    public User getUser(Integer id){
        return userService.getUser(id);
    }
    
    @RequestMapping("get_user")
    @ResponseBody
    public User getuser(@RequestBody String data){
        if(null != data){
            JSONObject po = JSON.parseObject(data);
            String id = po.get("id").toString();
            Integer userid = Integer.parseInt(id);
            return userService.getUser(userid);
        }
        return null;
    }
    
    @RequestMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        String names = "zhangsan,lisi";
        return userService.getUsers(names);
    }
    
    @RequestMapping("/listUser")
    @ResponseBody
    public List<User> getUserList(){
        return userService.getUserList();
    }
    
    @RequestMapping("meth1")
    @ResponseBody
    public String meth1(String str){
        String s = str + "meth1";
        return s;
    }
    
    @RequestMapping("meth2")
    @ResponseBody
    public Object meth2(String str){
        String string = meth1("ceshi");
        string = string + "meth2";
        System.out.println(string);
        List<User> users = getUsers();
        return users;
    }
    
    @RequestMapping("/addUser")
    @ResponseBody
    public Integer addUser(){
        User user = new User();
        user.setUsername("哈哈");
        user.setPassword("haha");
        user.setCreateTime(new Date());
        Integer m = userService.addUser(user);
        System.out.println(m);
        return m;
    }
    
    @RequestMapping("/server")
    @ResponseBody
    public String server(HttpServletRequest request){
        
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        
        String url = scheme + "==" + serverName + "==" + contextPath;
        
        return url;
    }
    
    @RequestMapping(value={"/getUserInfo/{id}","/getUserInfo"})
    @ResponseBody
    public User getUserInfo(@PathVariable(required=false,name="id")Integer id){
        System.out.println("id = "+id);
        User user = userService.getUser(id);
        return user;
    }
    
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(){
        String str = "{\"username\":\"张三\",\"password\":\"123\",\"createTime\":\"2019-04-12\",\"code\":\"0\",\"msg\":\"信息\"}";
        UserResult userResult = JSON.parseObject(str, UserResult.class);
        userService.addUser(userResult);
        return null;
    }
    
    @RequestMapping("img")
    @ResponseBody
    public String img(@RequestParam String str){
        System.out.println(str);
        return str;
    }
    
    @RequestMapping("/fuzzySearch")
    @ResponseBody
    public List<User> fuzzySearch(@RequestParam String param){
        return userService.fuzzySearch(param);
    }
    
    @RequestMapping("/arrTest")
    @ResponseBody
    public String arrTest(@RequestParam("ids[]")Integer[] ids, @RequestParam("status")String status){
        System.out.println(Arrays.asList(ids));
        System.out.println(status);
        return "success";
    }
    
//    @Autowired
//    private RedisService redisService;
//    
//    @RequestMapping("setValue")
//    @ResponseBody
//    public String setValue(){
//        //redisService.setex("username", "lisi", -1);
//        
//        User user = new User();
//        user.setUsername("张三");
//        user.setPassword("123");
//        user.setCreateTime(new Date());
//        
//        redisService.setex("user", user, 60);
//        
//        User obj = redisService.getObj("user", User.class);
//        System.out.println(obj);
//        return null;
//    }
    
    @RequestMapping("updateUser")
    @ResponseBody
    public Integer updateUser(){
        User user = new User();
        user.setId(7);
        user.setUsername("测试");
        //user.setPassword("789");
        user.setCreateTime(new Date());
        Integer m = userService.upateUser(user);
        return m;
    }
    
}
