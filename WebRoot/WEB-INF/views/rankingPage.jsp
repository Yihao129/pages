<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="css/bb/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/mine/rankingPage.css">
    <link rel="SHORTCUT ICON" href="images/wLog.jpg"/>
    <title>不辜负每一个清晨</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, 
                                     initial-scale=1.0, 
                                     maximum-scale=1.0, 
                                     user-scalable=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body id="body" background="http://r.photo.store.qq.com/psb?/60977a33-90b2-4538-9a57-2cdcbc04e198/C3ZjnMWVXh0QL7efLE7bhvW7orIvPi579tLTSn6OC0w!/r/dHwBAAAAAAAA" style="background-attachment: fixed;background-size: 100%">
    
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="signPage" class="navbar-brand">WAKE UP</a>
				<button id="controlB" class="navbar-toggle " data-toggle="collapse" data-target="#myCollapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
		<div class="collapse navbar-collapse" id="myCollapse">
			<ul class="nav navbar-nav navbar-right" style="margin-top:0;">
				<li class="active"><a href="signPage"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
				<li><a href="rankingPage"><span class="glyphicon glyphicon-list"></span> 总排名</a></li>
				<li><a id="login_out"href="#"><span class="glyphicon glyphicon-arrow-left"></span> 退出登录</a></li>
			</ul>	
		</div>
		
		</div>
	</div>
	
	
	
	
	<div class="container" style="margin-top:50px;">
		<table class="table" id="rankingTable">
		
		<center><h1 id="th1">总共签到</h1></center>
	<thead>
		<tr>
			<th >排序</th>
			<th >昵称</th>
			<th >签到次数</th>
			<th >本月积分</th>
		</tr>
	</thead>
</table>
	</div>
	
    
    <script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap.min.js"></script>	
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap-table.js"></script>	
	<script type="text/javascript" src="javascripts/mine/rankingPage.js"></script>
  </body>
</html>
