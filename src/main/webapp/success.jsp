<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var key = "${param.key}";
    var h1= document.getElementById("key").innerHTML = key;
    
    function inp(){
    	alert(key);
    }
    
    var msg = "${ajaxResult.msg}";
    console.log(msg);
    
</script>
<h3 id="key"></h3>
<h1>成功: </h1>${requestScope.ajaxResult.msg}
<input id="inp" type="button" value="点击测试" onclick="inp()">
