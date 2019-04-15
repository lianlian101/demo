<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	function tt() {
		$.ajax({
			url : "${pageContext.request.contextPath}/file/getImage",
			type : "post",
			success : function(data) {
				console.log(data);
				document.getElementById('img').setAttribute("src","data:image/jpeg;base64,"+data); 
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
        <%-- <form id="ff" action="${pageContext.request.contextPath}/file/getImage" method="post">
            <input type="submit" value="提交">
        </form> --%>
        
        <img id="img" alt="" src="">
        
        <button type="button" onclick="tt()">ceshi</button>
    </div>
</body>
</html>