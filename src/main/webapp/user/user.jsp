<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/js/jquery.serializejson.js"></script>
<!-- <script type="text/javascript" src="/js/formatCheck.js"></script> -->
<script type="text/javascript">
	function btn() {
		$.ajax({
		    url: "${pageContext.request.contextPath}/user/index",
		    data: $('#ff').serialize(),
		    type: "POST",
		    contentType: "application/json;charset=UTF-8",
		    dataType: "json",
		    success: function (date) {
		        console.log(date);
		    },
		    error : function() {
		        alert("异常！");
		    }
		}); 
	}
	
</script>
<style type="text/css">
    div {
    	padding-bottom: 10px;
    }
    
    input {
    	margin-bottom: 10px;
    }
</style>
</head>
<body>
    <div>
        <input type="button" value="提交" onclick="btn()" />
    </div>
    
    <div id="dd" style="display: none">
        <input id="username" type="text" name="username">
        <input id="password" type="text" name="password">
    </div>
</body>
</html>