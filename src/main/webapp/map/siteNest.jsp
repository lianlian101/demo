<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function ol(){
        	var lnglat = document.getElementById('ff').contentWindow.getUpdateLnglat();
        	alert(lnglat);
        }
    </script>
  </head>
  <body>
    <input type="button" value="点击" onclick="ol()">
    
    <iframe id="ff" src="site2.jsp" style="height: 100%; width: 100%"></iframe>
  </body>
</html>