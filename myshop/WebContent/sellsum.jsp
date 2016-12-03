<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="com"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/echarts.js"></script>
<script src="js/jquery-3.1.0.min.js"></script>
<title>销量统计</title>
</head>
<body>
	<com:navbar site="sell"></com:navbar>
	<div class="container" id="main">
		<div class="row">
			<h1 class="text-center whitetext loginhead">MySpace商品销量统计</h1>
		</div>
		<div class="row">
			<div id="chart" style="width: auto; height: 500px;"></div>
		</div>
	</div>
	<script>
		var myChart = echarts.init(document.getElementById('chart'));
		myChart.setOption({
			title : {
				text : 'MySpace商城商品销量图',
				show : false
			},
			color:['#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			backgroundColor : '#ffffff',
			tooltip : {},
			legend : {
				data : [ '销量' ],
				show : false
			},
			xAxis : {
				data : [],
				nameTextStyle:{
					fontSize:20
				},
				axisLabel:{
					show:true,
					interval:0,
					rotate:35
				}
			},
			dataZoom:[{
				type:'inside'
			}],
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				label:{
					normal:{
						show:true,
						position:'top',
						textStyle:{
							fontSize:14
						}
					}
				},
				data : []
			} ]
		});

		myChart.showLoading();
		$.ajax({
			type : "post",
			async : true,
			url : "ShowSellSumServlet",
			data : {},
			dataType : "json",
			success : function(result) {
				if (result) {
					myChart.hideLoading();
					myChart.setOption({
						xAxis : {
							data : result.categories
						},
						series : [ {
							name : '销量',
							data : result.data
						} ]
					});
				} else {
					alert("hello");
				}
			}
		});
		$(window).resize(function() {
			myChart.resize();
		});
	</script>
</body>
</html>