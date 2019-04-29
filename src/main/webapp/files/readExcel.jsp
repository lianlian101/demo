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
<body>
    <div>
        <form id="ff" action="/file/readExcel" method="post" enctype="multipart/form-data">
            <input id="file" name="file" type="file" accept="*.xlsx,*.xls">
            <input type="submit" value="提交">
        </form>
    </div>
    
    <div>
        <form id="ff2" action="javascript:void(0);" method="post" enctype="multipart/form-data">
            <input id="file" type="file" name="file">
            <input type="button" value="检验" onclick="checkfile()">
        </form>
    </div>
    <script type="text/javascript">
        function checkfile(){
        	$.ajax({
                url: '/file/readExcel',
                type: 'post',
                cache: false,
                data: new FormData($('#ff2')[0]),
                processData: false,
                contentType: false
            })
            .done(function(res) {
            	console.log(res);
            })
            .fail(function(res) {
            	console.log("操作失败",res);
            });
        }
    </script>
</body>
</html>