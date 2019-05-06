<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        if(typeof(WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        }else{
            console.log("您的浏览器支持WebSocket");
            var socket;
            
            var id = "${param.id}";
            var localpath = window.location.host;
            var url = "ws://" + localpath + "/websocket/" + id;
            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            socket = new WebSocket(url);
            //打开事件
            socket.onopen = function() {
                console.log("Socket 已打开");
            };
            //关闭事件
            socket.onclose = function() {
                console.log("Socket已关闭");
            };
            //发生了错误事件
            socket.onerror = function() {
                alert("Socket发生了错误");
            }
            //关闭连接
            function closeWebSocket() {
                socket.close();
            }
            //发送消息
            function send(message) {
                socket.send(message);
            }
            //获得消息事件
            socket.onmessage = function(msg) {
                console.log("收到的消息：",msg.data);
                //发现消息进入,调后台获取
                insertData(msg.data);
            };
            
            // 监听窗口关闭
            window.onbeforeunload = function(e){     
        	　　closeWebSocket();
        	} 
        }
        
        // 发送消息
        function sendmsg(){
        	var msg = document.getElementById("msg").value;
        	send(msg);
        }
        
        // 给p标签赋值
        function insertData(data){
        	document.getElementById("pp").innerText = data;
        }
    </script>
  </head>
  <body>
    <input id="msg" type="text"><br><br>
    <button type="button" onclick="sendmsg()">发送消息</button>
    <br><br>
    <p id="pp"></p>
  </body>
</html>