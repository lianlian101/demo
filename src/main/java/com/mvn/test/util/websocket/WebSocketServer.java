package com.mvn.test.util.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// @ServerEndpoint(value = "/websocket") // 也可以不指定参数
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketServer {

    // 日志提示
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<Integer,WebSocketServer> webSocketMap = new ConcurrentHashMap<Integer,WebSocketServer>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    // 当前登录用户id
    private Integer userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")Integer userId) {
        this.session = session;
        this.userId = userId;
        webSocketMap.put(this.userId, this); // 加入map中
        addOnlineCount(); // 在线数加1

        log.debug("用户  {} 加入，当前在线人数为：{}", userId, getOnlineCount());

        // TODO 连接成功后，可以检测当前用户的未读消息，然后提醒用户或显示在用户的未读消息列表上
        try {
            // 发送连接成功，只是为了提示用户连接成功，测试用
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.userId); // 从map中删除
        subOnlineCount(); // 在线数减1
        
        log.debug("有一连接关闭，当前在线人数为：{}", getOnlineCount());
    }

    /**
     * 链接发送错误时调用的方法
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("连接发送异常：{}", error.getMessage());
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.debug("来自客户端的消息：{}", message);
        
        // 遍历所有连接的用户，群发消息，也可以只给指定用户发送消息
        webSocketMap.forEach((id, webSocketServer)->{
            System.out.println("遍历的用户id为：" + id);
            // TODO 可以根据条件发送
            // 只给其他用户发送，自己不接收自己发送的消息
            if(!id.equals(this.userId)){
                try {
                    webSocketServer.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
    
    /**
     * 创建日期: 2019年5月6日 创建人: zhb 说明: 主要是这段，使用这个方法，可以实现服务器主动推送
     * 
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        webSocketMap.forEach((id, webSocketServer)->{
            try {
                webSocketServer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}
