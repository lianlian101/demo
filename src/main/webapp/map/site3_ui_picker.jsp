<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">

<head>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PoiPicker/examples/index.html -->
    <base href="//webapi.amap.com/ui/1.0/ui/misc/PoiPicker/examples/" />
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>输入框选择POI点</title>
    <style>
    html,
    body,
    #container {
        width: 100%;
        height: 100%;
        margin: 0px;
        font-size: 13px;
    }
    
    #pickerBox {
        position: absolute;
        z-index: 9999;
        top: 50px;
        right: 30px;
        width: 300px;
    }
    
    #pickerInput {
        width: 200px;
        padding: 5px 5px;
    }
    
    #poiInfo {
        background: #fff;
    }
    
    .amap_lib_placeSearch .poibox.highlight {
        background-color: #CAE1FF;
    }
    
    .amap_lib_placeSearch .poi-more {
        display: none!important;
    }
    </style>
</head>

<body>
    <div id="container" class="map" tabindex="0"></div>
    <div id="pickerBox">
        <input id="pickerInput" placeholder="输入关键字选取地点" />
        <div id="poiInfo"></div>
    </div>
    <script type="text/javascript" src='//webapi.amap.com/maps?v=1.4.15&key=93e4489848c8979b2b2943707e6b7ec0'></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <script type="text/javascript">
        var map = new AMap.Map('container', {
            zoom: 10
        });
    
        AMapUI.loadUI(['misc/PoiPicker'], function(PoiPicker) {
            var poiPicker = new PoiPicker({
                //city:'北京',
                input: 'pickerInput'
            });
            //初始化poiPicker
            poiPickerReady(poiPicker);
        });
    
        function poiPickerReady(poiPicker) {
            window.poiPicker = poiPicker;
            var marker = new AMap.Marker();
            var infoWindow = new AMap.InfoWindow({
                offset: new AMap.Pixel(0, -20)
            });
            //选取了某个POI
            poiPicker.on('poiPicked', function(poiResult) {
                //var source = poiResult.source,
                poi = poiResult.item,
                info = {
                    //source: source,
                    //id: poi.id,
                    name: poi.name,
                    location: poi.location.toString(),
                    address: poi.address
                };
                marker.setMap(map);
                infoWindow.setMap(map);
    
                marker.setPosition(poi.location);
                infoWindow.setPosition(poi.location);
    
                infoWindow.setContent('POI信息: <pre>' + JSON.stringify(info, null, 2) + '</pre><button type="button" onclick="sub()">确定</button>');
                infoWindow.open(map, marker.getPosition());
    
                //map.setCenter(marker.getPosition());
            });
    
            /* poiPicker.onCityReady(function() {
                poiPicker.suggest('美食');
            }); */
        }
        
        function sub(){
        	console.log("点击");
        }
    </script>
</body>

</html>