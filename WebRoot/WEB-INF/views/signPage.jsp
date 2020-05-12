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
     <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="css/mine/signPage.css">
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
  
  <body id="body" background="http://r.photo.store.qq.com/psb?/60977a33-90b2-4538-9a57-2cdcbc04e198/C3ZjnMWVXh0QL7efLE7bhvW7orIvPi579tLTSn6OC0w!/r/dHwBAAAAAAAA" style="background-size: 100%;background-attachment: fixed;">
    
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
	
	<div class="row show-grid"   style="margin-top:55px;margin-bottom:5px;height:15px;">              
          <div class="col-xs-9" style="height:25px;">
          <span id="msg" style="color:blue;text-align:center;font-size:20px;margin-top:13px;"></span>
          </div>
          <div class="col-xs-3" style="height:25px;">
          	<span id="clear" style="color:#CD0000;text-align:center;font-size:20px;margin-top:13px;">^.^</span>
          </div>
    </div>
    
    <div id="alr_div" style="height:20px;display:none;">
	<center>
		  <span id="alr_show" style="float:center;font-size:18px;">23 * 78 =</span>
  		  <input id="alr_result" style="float:center;font-size:18px;height:20px;width:60px;border-bottom:1px solid #005aa7;border-top:0px; border-left:0px;border-right:0px;text-align:center;">
		  <span id="one_more_time" class="btn">换一个</span>
		  <span id="submitIt" class="btn">提交</span>
	</center>
	</div>
	<hr />
	
	<div class="tab1" >
		<div class="container">
			<center ><h3 id="signCountShowH1">您已签到 <lable id="signCount"><%= session.getAttribute("signCount")%></lable> 次,
			连续签到 <lable id="seriesCount">3</lable> 天,</lable>
			积分为  <lable id="points">3</lable></h3>
			</center>
			<button  id="btn_sign" class="btn btn-default center-block btn-lg btn-danger">签到</button>
		</div>
	</div>
	

	



	
	
        

	
	<div class="container">
		<table class="table" id="myTable">
		
		<center><h1 id="th1">今日签到</h1></center>
	<thead>
		<tr>
			<th >排序</th>
			<th >昵称</th>
			<th >签到时间</th>
			<th >连续签到次数</th>
			<th >积分</th>
		</tr>
	</thead>
</table>
	</div>
	
    
    <script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap.min.js"></script>		
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap-table.js"></script>	
	<script type="text/javascript" src="javascripts/mine/signPage.js"></script>
  </body>
</html>
