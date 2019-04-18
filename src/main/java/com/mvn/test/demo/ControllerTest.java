package com.mvn.test.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvn.test.Application;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)//这里的Application是springboot的启动类名
//@WebAppConfiguration
//public class ControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Before
//    public void setupMockMvc() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testSend() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser")
//                    .contentType(MediaType.ALL)
//                    .content(mapper.writeValueAsString(1)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.ALL))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//    
//}

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser").accept(MediaType.APPLICATION_JSON).param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("结果：" + str);
    }
    
    @Test
    public void getuser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/get_user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{'id':1}"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("结果："+str);
    }
    
    
}

