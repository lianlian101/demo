<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <title>位置和点标记和点击</title>
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
        <script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
        <script src="https://webapi.amap.com/maps?v=1.4.15&key=93e4489848c8979b2b2943707e6b7ec0"></script>
        <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
        <style>
            html, body, #container {
            	width: 100%;
            	height: 100%;
            }
        </style>
    </head>
    <body>
        <%-- 地图显示位置 --%>
        <div id="container"></div>
    
        <script>
            // 经纬度
            var lnglat = [117.201538, 39.085294];
            // 点标记
            var marker;
        	//初始化地图
        	var map = new AMap.Map('container', {
        		resizeEnable : true, //是否监控地图容器尺寸变化
        		zoom : 12, //初始地图级别
        		center : lnglat, //初始地图中心点
        		showIndoorMap : false //关闭室内地图
        	});
        	// 设置点标记
        	function setMarker(lnglat){
            	// 构造点标记
            	marker = new AMap.Marker({
            		icon : "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            		position : lnglat
            	});
        		map.add(marker);
                map.setFitView();
        	}
            // 初始化点标记
        	setMarker(lnglat);
        	// 移除点标记
        	function delMarker(){
        	    map.remove(marker);
        		map.setFitView();
        	}
        	// 为marker添加点击事件
        	function addMarkerClick(){
        		marker.on('click', function(e){
                    console.log("经纬度：", e.lnglat);
                });
        	}
        	// 为初始化点标记添加点击事件
        	addMarkerClick();
        	// 全局添加点击事件
            map.on('click', function(e){
                let lnglat = [e.lnglat.lng, e.lnglat.lat];
                 // 移除上一个点标记
                delMarker(marker);
                // 设置新的点标记
                setMarker(lnglat);
                // 为marker添加点击事件
                addMarkerClick();
            });
        	
        	
        </script>
    </body>
</html>