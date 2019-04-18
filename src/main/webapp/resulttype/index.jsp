<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        window.onload = function () {
        	document.getElementById("btn").onclick = function (){
        		$.ajax({
        			url: "/method/method_test",
        			data: JSON.stringify({"name":"zs","age":12}),
        			//data: {"name":"zx","age":12},
        			type: "post",
        			contentType: "application/json",
        			//dataType: "json",
        			success: function(data){
        				console.log(data);
        			},
        			error: function(xhr){
        				console.log(xhr);
        			}
        		});
        	}
        	
        	document.getElementById("btn2").onclick = function (){
                $.ajax({
                    url: "/method/demo1",
                    data: {"code":"demo1"},
                    type: "post",
                    success: function(data){
                        console.log(data);
                    },
                    error: function(xhr){
                        console.log(xhr);
                    }
                });
            }
        }
    </script>
  </head>
  <body>
    <button id="btn" type="button">测试</button>
    
    <button id="btn2" type="button">测试2</button>
  </body>
</html>