<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function getUser(){
        	$.get("/restful/user",function(data){
        		console.log(data);
        	});
        }
        
        function addUser(){
            $.post("/restful/user",function(data){
            	console.log(data);
            });
        }
        
        function updateUser(){
        	$.ajax({
        		url: "/restful/user",
        		type: "put",
        		success: function(data){
        			console.log(data);
        		},
        		error: function(xhr){
        			console.log(xhr);
        		}
        	});
        }
        
        function deleteUser(){
        	$.ajax({
                url: "/restful/user",
                type: "delete",
                success: function(data){
                    console.log(data);
                },
                error: function(xhr){
                    console.log(xhr);
                }
            });
        }
    </script>
  </head>
  <body>
    <input type="button" value="获取信息" onclick="getUser()">
    <br><br>
    <input type="button" value="添加信息" onclick="addUser()">
    <br><br>
    <input type="button" value="更新信息" onclick="updateUser()">
    <br><br>
    <input type="button" value="删除信息" onclick="deleteUser()">
  </body>
</html>