<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <title>位置和点标记和点击和信息窗体</title>
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
        <script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
        <script src="https://webapi.amap.com/maps?v=1.4.15&key=93e4489848c8979b2b2943707e6b7ec0&plugin=AMap.Autocomplete"></script>
        <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
        <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
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
    
        <%-- 搜索框 --%>
        <div class="info">
            <div class="input-item">
                <div class="input-item-prepend">
                    <span class="input-item-text" style="width: 8rem;">请输入关键字</span>
                </div>
                <input id='tipinput' type="text">
            </div>
        </div>
    
        <script>
            // 经纬度
            var lnglat = [117.201538, 39.085294];
            
            // 修改过后确定的经纬度
            var updateLnglat = lnglat;
            // 随点击一直变动的经纬度
            var lnglatChange = lnglat;
        
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
            	let marker = new AMap.Marker({
            		icon : "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            		position : lnglat
            	});
        		map.add(marker);
                map.setFitView();
                return marker;
        	}
        	// 移除点标记
        	function delMarker(marker){
        	    map.remove(marker);
        		map.setFitView();
        	}
        	// 为marker添加点击事件
            function addClickOfMarker(marker){
                marker.on('click', function(e){
                    console.log("经纬度：", e.lnglat);
                    openInfo();
                })
            }
        	
        	// 初始化设置标记点
            var marker = setMarker(lnglat);
        	addClickOfMarker(marker);
            
        	//输入提示
        	var auto = new AMap.Autocomplete({
        		input : "tipinput"
        	});
        	var autocomplete= new AMap.Autocomplete(auto);
        	AMap.event.addListener(autocomplete, "select", function(e){
        		let poi = e.poi;
        		// 名字
        		let name = poi.name;
        		// 经纬度
        		let lnglat = [poi.location.lng, poi.location.lat];
        		// 设置中心点
        		map.setCenter(lnglat);
        		evenList(lnglat);
    	   });
        	
        	// 窗体
        	var infoWindow;
        	//在指定位置打开信息窗体
            function openInfo() {
                //构建信息窗体中显示的内容
                infoWindow = new AMap.InfoWindow({
                    anchor: 'top-left',
                    content: `
                        <div>
                            <p>将此点标记为新的位置？</p>
                            <br>
                            <input type='button' value='确定' onclick='confirm()' style='margin-left: 70%'/>
                        <div>
                    `
                });
                // 打开信息窗体
                infoWindow.open(map, marker.getPosition())
                lnglatChange = [marker.getPosition().lng, marker.getPosition().lat];
                console.log('变动的经纬度：', lnglatChange, "确定的经纬度：", updateLnglat);
            }
        	// 关闭信息窗体
        	function closeInfo(){
        		 infoWindow.close();
        	}
        	
        	// 添加点击事件
        	map.on('click', function(e){
        		let lnglat = [e.lnglat.lng, e.lnglat.lat];
        		evenList(lnglat);
        	});
        	
        	// 事件集合
        	function evenList(lnglat){
        		// 移除上一个点标记
                delMarker(marker);
                // 设置新的点标记
                marker = setMarker(lnglat);
                // 为新的点标记添加点击事件
                addClickOfMarker(marker);
                // 打开信息窗体
                openInfo();
        	}
        	
        	// 确定新的坐标位置
        	function confirm(){
        		updateLnglat = lnglatChange;
        		closeInfo();
        		console.log('确定的经纬度：', updateLnglat);
        	}
        	
        	// 获取确定的经纬度
        	function getUpdateLnglat(){
        		return updateLnglat;
        	}
        </script>
    </body>
</html>