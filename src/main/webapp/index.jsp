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
		/* $.ajax({
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
		}); */
	    
		function Email(name) {
			this.email = name;
		}
		var email = document.getElementById("email").value;
		var emailObj = new Email(email);

		$.ajax({
			url : "${pageContext.request.contextPath}/user/index?key=1",
			data : JSON.stringify(emailObj),
			type : "post",
			//dataType: 'json',
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				if (data.trim() != 0) {
					//document.getElementById("bb").html.replaceWith(data);
					//document.body.innerHTML = document.body.innerHTML.replace(data);
					$('body').html(data);
				} else {
					alert("失败");
				}
			},
			error : function() {
				alert("异常！");
			}
		});

	}

	function btn2() {
		$.ajax({
			url : "${pageContext.request.contextPath}/user/index2",
			data : JSON.stringify($('#ff').serializeJSON()),
			type : "post",
			//dataType: 'json',
			contentType : "application/json",
			success : function(data) {
				console.log(data);
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function jsonstr() {
		var data = {
			name : "张三",
			age : 12,
			money : 100
		};
		console.log(data);

		var dataJson = JSON.stringify(data);
		console.log(dataJson);

		var dataJsonObj = JSON.parse(dataJson);
		console.log(dataJsonObj);
	}

	function tt() {
		$.ajax({
			url : "${pageContext.request.contextPath}/user/index3",
			data : JSON.stringify({
				/* "key" : "index3" */
			}),
			type : "post",
			//dataType: 'json',
			contentType : "application/json",
			success : function(data) {
				console.log(data);
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
<body id="bb">
    <div>
        <input id="email" name="email" type="text" /> 
        <input type="button" value="提交" onclick="btn()" />
    </div>
    <div>
        <form id="ff" action="/url" method="post">
            <input id="name" name="name" type="text">
            <input id="password" name="name" type="text"> 
            <input type="button" value="表单序列化测试" onclick="btn2()">
        </form>
    </div>
    <div>
        <form id="ff" action="javascript:void(0);" method="post">
            <input id="name" name="name" type="text"> 
            <input id="password" name="name" type="text"> 
            <input type="button" value="表单序列化测试" onclick="btn2()">
        </form>
    </div>
    <div>
        <form id="ff" action="javascript:void(0);" method="post">
            <input id="name" name="name" type="text"> 
            <input id="password" name="name" type="text"> 
            <input type="button" value="表单序列化测试" onclick="btn2()">
        </form>
    </div>

    <div>
        <input id="jsonstr" type="button" value="json序列化" onclick="jsonstr()">
    </div>
    <div>
        <input id="tt" type="button" value="测试" onclick="tt()">
    </div>
    <div>
        <input id="rule" type="button" value="校验" onclick="checkStr()">
    </div>
</body>
</html>