package com.mvn.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.util.websocket.WebSocketServer;

@Controller
@RequestMapping("/websocket/")
public class WebSocketServerController {

    @RequestMapping(value = "pushmsgtoweb")
    @ResponseBody
    public Map<String,Object> pushVideoListToWeb(@RequestParam String param) {
        Map<String,Object> result =new HashMap<String,Object>();
        
        try {
            WebSocketServer.sendInfo("有新客户呼入");
            result.put("result", true);
        }catch (IOException e) {
            result.put("result", true);
        }
        return result;
    }

    
}
