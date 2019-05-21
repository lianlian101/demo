<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <title>点聚合</title>
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
        <style>
            html, body, #container {
            	height: 100%;
            	width: 100%;
            }
            
            .input-card {
            	width: 25rem;
            }
            
            .input-card .btn {
            	width: 7rem;
            	margin-right: .7rem;
            }
            
            .input-card .btn:last-child {
            	margin-right: 0;
            }
        </style>
    </head>
    <body>
        <!-- 地图显示区域 -->
        <div id="container" class="map" tabindex="0"></div>
        
        <script src="//a.amap.com/jsapi_demos/static/china.js"></script>
        <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=93e4489848c8979b2b2943707e6b7ec0&plugin=AMap.MarkerClusterer"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
        <script type="text/javascript">
        
            var cluster, markers = [];
        
            var map = new AMap.Map("container", {
                resizeEnable: true,//是否监控地图容器尺寸变化
                //center: [105, 34],
                center: [116.397428, 39.90923], //初始化地图中心点
                zoom: 4 //初始化地图层级
            });

            //var points = [[116.39, 39.9],[117.39, 40.9],[114.39, 36.9]];
            var points = [], sites = [];
            $.ajax({
            	url: "${pageContext.request.contextPath}/map/location.json",
            	data: {},
            	//type: "post",
            	async: false,
            	dataType: "json",
            	success: function(data){
            		sites = data;
            	}
            });
            /* console.log(sites);
            console.log(sites.length); */
            for(let i = 0, len = sites.length; i < len; i++){
            	let lnglat = sites[i].lnglat;
            	points[i] = lnglat;
            }
            
            for (let i = 0, len = points.length; i < len; i++) {
                markers.push(new AMap.Marker({
                    position: points[i],
                    offset: new AMap.Pixel(-15, -15)
                }))
                
                // 为每一个点添加点击事件
                markers[i].on('click',function(e){
                	let lnglatByMap = e.target.B.position; // 传入时的经纬度
                	let lng_lat = [lnglatByMap.lng, lnglatByMap.lat];
                	let lnglat = null, lng = null, lat = null, projectId = null;
                	for(let i = 0, len = sites.length; i < len; i++){
                        lnglat = sites[i].lnglat;
                        lng = lnglat[0];
                        lat = lnglat[1];
                        if(lng == lng_lat[0] && lat == lng_lat[1]){
                        	projectId = sites[i].projectId;
                        }
                    }
                });
            }
            
            // 聚合坐标点，显示位置，设置样式
            var count = markers.length;
            var _renderClusterMarker = function (context) {
                var factor = Math.pow(context.count / count, 1 / 18);
                var div = document.createElement('div');
                var Hue = 180 - factor * 180;
                var bgColor = 'hsla(' + Hue + ',100%,50%,0.7)';
                var fontColor = 'hsla(' + Hue + ',100%,20%,1)';
                var borderColor = 'hsla(' + Hue + ',100%,40%,1)';
                var shadowColor = 'hsla(' + Hue + ',100%,50%,1)';
                div.style.backgroundColor = bgColor;
                var size = Math.round(30 + Math.pow(context.count / count, 1 / 5) * 20);
                div.style.width = div.style.height = size + 'px';
                div.style.border = 'solid 1px ' + borderColor;
                div.style.borderRadius = size / 2 + 'px';
                div.style.boxShadow = '0 0 1px ' + shadowColor;
                div.innerHTML = "项目数：" + context.count;
                //div.style.lineHeight = size + 'px';
                div.style.color = fontColor;
                div.style.fontSize = '14px';
                div.style.textAlign = 'center';
                context.marker.setOffset(new AMap.Pixel(-size / 2, -size / 2));
                context.marker.setContent(div)
            };
        
            // 点聚合显示的图标的样式
            (function addCluster() {
                cluster = new AMap.MarkerClusterer(map, markers, {
                    gridSize: 80,
                    renderClusterMarker: _renderClusterMarker
                });
            })()
        </script>
    </body>
</html>