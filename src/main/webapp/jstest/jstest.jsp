<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    function findIndexOl(){
    	var str = "001,002,001,201";
        var strs = str.split(",");
        /* console.log(strs);
        console.log(strs.indexOf("00"));
        console.log(strs.indexOf("001")); */
        
        /* var idx = strs.findIndex(function(value,index,arr){
        	return value == "001";
        });
        console.log(idx); */
        
        /* console.log(str.indexOf("00"));
        console.log(str.indexOf("001")); */
        
        var index = $.inArray("001", strs);
        console.log(index);
    }
</script>
</head>
<body>
    <input type="button" value="findIndex测试" onclick="findIndexOl()">
</body>
</html>