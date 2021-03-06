package com.mvn.test.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mvn.test.entity.User;
import com.mvn.test.entity.pojo.UserArr;
import com.mvn.test.entity.pojo.UserResult;
import com.mvn.test.service.UserService;
import com.mvn.test.util.pojo.JsonResult;

@Controller
@RequestMapping("/user/")
public class UserController {
    
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @RequestMapping("index")
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
    
    @RequestMapping("index2")
    @ResponseBody
    public String index2(@RequestBody String data){
        
        System.out.println("json is: " + data);
        JSONObject parse = JSON.parseObject(data);
        System.out.println("解析后的数据为: " + parse);
        
        return "index2";
        
    }
    
    @RequestMapping("index3")
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
    
    @RequestMapping("getUsers")
    @ResponseBody
    public List<User> getUsers(){
        String names = "zhangsan,lisi";
        return userService.getUsers(names);
    }
    
    @RequestMapping("listUser")
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
    
    @RequestMapping("addUser")
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
    
    @RequestMapping("server")
    @ResponseBody
    public String server(HttpServletRequest request){
        
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        
        String url = scheme + "==" + serverName + "==" + contextPath;
        
        return url;
    }
    
    @RequestMapping(value={"getUserInfo/{id}","getUserInfo"})
    @ResponseBody
    public User getUserInfo(@PathVariable(required=false,name="id")Integer id){
        System.out.println("id = "+id);
        User user = userService.getUser(id);
        return user;
    }
    
    @RequestMapping("insert")
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
    
    @RequestMapping("fuzzySearch")
    @ResponseBody
    public List<User> fuzzySearch(@RequestParam String param){
        return userService.fuzzySearch(param);
    }
    
    @RequestMapping("fuzzySearch2")
    @ResponseBody
    public List<User> fuzzySearch2(@RequestParam String param){
        return userService.fuzzySearch2(param);
    }
    
    @RequestMapping("arrTest")
    @ResponseBody
    public String arrTest(@RequestParam("ids[]")Integer[] ids, @RequestParam("status")String status){
        System.out.println(Arrays.asList(ids));
        System.out.println(status);
        return "success";
    }
        
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
    
    @RequestMapping("log")
    public String log(){
        try {
            //int i = 1/0;
        } catch (Exception e) {
            log.error("异常：{}", e.getMessage(), e);
        }
        return "hello";
    }
    
    @RequestMapping("arr")
    @ResponseBody
    public UserArr arr(@RequestBody UserArr userArr){
        System.out.println(userArr);
        return userArr;
    }
    
    @RequestMapping("serialize")
    @ResponseBody
    public Object serialize(User user){
        return user;
    }
    
    @RequestMapping("getParamMap")
    @ResponseBody
    public Map<String, String[]> getParamMap(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String[]> map = new HashMap<>();
        Iterator<Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String[]> entry = iterator.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            if(!(value != null && value.length == 1 && value[0].equals(""))){
                map.put(key, value);
            }
        }
        return map;
    }
    
    @RequestMapping("req")
    public String req(HttpServletRequest request){
        User user = new User(1,"张三","123",new Date(),1);
        request.setAttribute("user", user);
        request.setAttribute("json", JSON.toJSON(user));
        String str = "'{\"id\":1,\"username\":\"张三\",\"password\":\"123\",\"createTime\":1562639744333}'";
        request.setAttribute("str", str);
        return "user/user";
    }
    
    /**
     * 日期：2019年8月22日
     * 作者：zhb
     * 说明：测试controller层的事务
     * 
     * @return
     */
    @RequestMapping("transactionTest")
    @ResponseBody
    @Transactional
    public String transactionTest(){
        User user = new User();
        user.setUsername("事务测试");
        userService.addUser(user);
        int m = 2/0;
        User user2 = new User();
        user2.setId(4);
        user2.setUsername("事务回滚");
        userService.upateUser(user2);
        return "success";
    }
    
}
