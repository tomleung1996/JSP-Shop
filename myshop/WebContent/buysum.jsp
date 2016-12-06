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
<script src="js/myscript.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link Rel="SHORTCUT ICON" href="image/favicon.ico">
<title>购买量统计</title>
</head>
<body>
	<com:navbar site="buy"></com:navbar>
	<div class="container" id="main">
		<div class="row">
			<h1 class="text-center whitetext loginhead">MySpace用户购买量统计</h1>
		</div>
		<div class="row">
			<div id="chart" style="width: auto; height: 500px;"></div>
		</div>
	</div>
	<script>
		var myChart = echarts.init(document.getElementById('chart'));
		myChart.setOption({
			title : {
				text : 'MySpace用户购买量统计',
				show : false
			},
			//color:['#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			backgroundColor : '#ffffff',
			tooltip : {},
			legend : {
				data : [ '购买量' ],
				show : false
			},
			tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
			xAxis : {
				data : [],
				nameTextStyle:{
					fontSize:20
				},
				axisLabel:{
					show:true,
					interval:0,
					rotate:0
				}
			},
			dataZoom:[{
				type:'inside'
			},{
				type:'slider',
				orient:'vertical',
				filterMode:'empty'
			}],
			yAxis : {},
			series : [ {
				name : '购买量',
				type : 'bar',
				label:{
					normal:{
						show:true,
						position:'right',
						textStyle:{
							fontSize:20
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
			url : "ShowBuySumServlet",
			data : {},
			dataType : "json",
			success : function(result) {
				if (result) {
					myChart.hideLoading();
					myChart.setOption({
						xAxis : {
							type:'value',
							data : result.data
						},
						yAxis:{
							type:'category',
							data :result.categories
						},
						series : [ {
							name : '购买量',
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