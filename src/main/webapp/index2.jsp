<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/js/jquery.serializejson.js"></script>
<script type="text/javascript" src="/js/formatCheck.js"></script>
<script type="text/javascript">
    
    function btn(){
        var b1 = checkEmail("qq@qq.com");
        console.log(b1);
        var b2 = checkPhone("13211156789");
        console.log(b2);
        var b3 = checkCode("123456");
        console.log(b3);
    }
    
</script>
<style type="text/css">
    div{padding-bottom: 10px;}
    input{margin-bottom: 10px;}
</style>
</head>
<body id="bb">
    <div>
		<button type="button" onclick="btn()">校验</button>
	</div>
</body>
</html>