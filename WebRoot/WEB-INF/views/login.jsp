<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, 
                                     initial-scale=1.0, 
                                     maximum-scale=1.0, 
                                     user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/bb/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="css/mine/main.css">
<link rel="SHORTCUT ICON" href="images/wLog.jpg"/>
<title>不辜负每一个清晨</title>
</head>
<body background="http://r.photo.store.qq.com/psb?/60977a33-90b2-4538-9a57-2cdcbc04e198/C3ZjnMWVXh0QL7efLE7bhvW7orIvPi579tLTSn6OC0w!/r/dHwBAAAAAAAA" style="background-size: 120%;background-attachment: fixed;">
	<div id="container" class="container" style="padding:30px;">


	<div class=" center-block" style="margin-bottom:20px;">
		
		<center><h1>Welcome to Wake Up </h1></center>
		
	
	</div>
	<div class="form-group">
		<label><span class="glyphicon glyphicon-phone"></span> email</label>
		<input id="email" type="text" class="form-control input-lg" placeholder="请输入您的邮箱"  />	
	</div>
	<div class="form-group">
		<label><span class="glyphicon glyphicon-lock"></span> password</label>
		<input id="pwd" type="password" class="form-control input-lg" placeholder="请输入您的密码"   />	
	</div>
	
	<div class="login_button" >
	<center style="margin-top:18px;">
	<button id="btn_register" class="btn btn-primary" style="margin-right:2px;" data-toggle="modal" data-target="#myModal">register</button>
	<button id="btn_submit" class="btn btn-primary">login</button>
	</center>
	</div>
	
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
					<h4 class="modal-title">register</h4>
				</div>
				<div  class="modal-body form-group">
					nick name:
					<input id="nameR" name="nameR" type="text" class="form-control">
					
					email:
					<input id="emailR" name="emailR" type="text" class="form-control">
					
					password:
					<input id="pwdR" name="pwdR" type="password" class="form-control">
					retype password:
					<input id="pwd2R" name="pwd2R" type="password" class="form-control">
					
					<div style="height:25px;"><sapn id="prop"></sapn></div>
					
				</div>
				<div class="modal-footer">
					<button id="btn_register2" class="btn btn-primary btn-lg">register</button>
					
				</div>
			</div>
		</div>

	</div>


	<div><span id="msg" style="height:30px;"></span></div>
	
	
	
	
	</div>
<script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/bootstrap/bootstrap.min.js"></script>	
<script type="text/javascript" src="javascripts/bootstrap/bootstrap-datetimepicker.min.js"></script>	
<script type="text/javascript" src="javascripts/bootstrap/bootstrap-datetimepicker.fr.js"></script>	
<script type="text/javascript" src="javascripts/bootstrap/bootstrap-datepicker.min.js"></script>	
<script type="text/javascript" src="javascripts/mine/login.js" charset="utf-8"></script>
 
</body>
</html>