<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!-- <script type="text/javascript" src="/js/jquery.serializejson.js"></script> -->
<!-- <script type="text/javascript" src="/js/formatCheck.js"></script> -->
<script type="text/javascript">
	function arrTest() {
		$.ajax({
		    url: "/user/arr",
		    data: JSON.stringify({
		    	"userId":1,
		    	"users":[1,2]
		    }),
		    type: "post",
		    contentType: 'application/json;charset=utf-8',
		    success: function (date) {
		        console.log(date);
		    },
		    error : function(xhr) {
		        alert(xhr);
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
    <input type="button" value="集合测试" onclick="arrTest()">
</body>
</html>