<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <title>位置和点标记</title>
        
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
        <script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
        <script src="https://webapi.amap.com/maps?v=1.4.15&key=93e4489848c8979b2b2943707e6b7ec0"></script>
        <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
        
        <style>
            html,
            body,
            #container {
              width: 100%;
              height: 100%;
            }
        </style>
    </head>
    <body>
        <div id="container"></div>
    
        <script type="text/javascript">
        	//初始化地图
        	var map = new AMap.Map('container', {
        		resizeEnable : true, //是否监控地图容器尺寸变化
        		zoom : 12, //初始地图级别
        		center : [ 117.201538, 39.085294 ], //初始地图中心点
        		showIndoorMap : false
        	//关闭室内地图
        	});
        
        	/* map.setZoom(zoom); //设置地图层级
        	map.setCenter([lng, lat]); //设置地图中心点
        	map.setZoomAndCenter(zoom, [lng, lat]); //同时设置地图层级与中心点 */
        
        	// 设置图片大小
        	var icon = new AMap.Icon({
        		size : new AMap.Size(40, 50), // 图标尺寸
        		image : 'https://www.baidu.com/img/bd_logo1.png', // Icon的图像
        		//imageOffset: new AMap.Pixel(0, 30),  // 图像相对展示区域的偏移量，适于雪碧图等
        		imageSize : new AMap.Size(40, 50)
        	// 根据所设置的大小拉伸或压缩图片 
        	});
        	// 构造点标记
        	var marker = new AMap.Marker({
        		icon : icon,
        		position : [ 117.201538, 39.085294 ]
        	});
        
        	// 设置点标记
        	map.add(marker);
        	map.setFitView();
        	// 移除点标记
        	/* map.remove(marker);
        	map.setFitView(); */
        </script>
    </body>
</html>