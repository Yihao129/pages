<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Pages</title>
    <link rel="icon" href="https://cdn1.iconfinder.com/data/icons/files-bolder/512/file_document_new_blank-512.png">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/codemirror.min.css" />
	<link rel="stylesheet" type="text/css" href="css/bb/css/bootstrap.min.css">
	
	<style>
	  .CodeMirror {
		  border: 2px solid #eee;
		  height: auto;
		  font-family: Arial, monospace;
		  font-size: 16px;
		  font-weight:bold;
		}
	
	  </style>
  </head>
  
  <body>
	<div class="navbar navbar-light navbar-fixed-top" >
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" style="font-weight:bold;" href="main">pages</a>
				<button id="controlB" class="navbar-toggle " data-toggle="collapse" data-target="#myCollapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
		<div class="collapse navbar-collapse" id="myCollapse">
			<ul class="nav navbar-nav navbar-right" style="margin-top:0;">
				<li class="" style="width:150px;text-align:center"><a id="login_name"> </a></li>
				<li class="active"><a id="login_href" href="login.do"><span class="glyphicon glyphicon-home"></span> <span id="login_text">登录</span></a></li>
			</ul>	
		</div>
		
		</div>
	</div>
  
  
  
  <br />
    
    <form style="height:85%;margin-top:40px;margin-bottom:0px;">
	  <div class="mb-3"  style="height:82%;">
		
		<label >page topic:</label>
		<input id="page_topic_input" name="nameR" type="text" class="form-control">
	    <label >Edit your page:</label>
	    <textarea id="htmlInput" class="form-control" id="validationTextarea" placeholder="Required example textarea"  ></textarea>
	  </div>
	
	      
	</form>
	<center><button id='view_btn' class="btn btn-primary" style="margin-right:2px;">view</button></center>
	
<div class="modal fade" id="myModal" >
		<div class="modal-dialog" style="margin:0px 0px;width:100%;height:100%;">
			<div class="modal-content" style="height:100%;" >

				<div  id="htmlContent" class="modal-body form-group" style="height:90%;">
					
					
				</div>
			
				<div class="modal-footer" style="padding:9px;">
					<center>
						<button id="back_btn" class="btn btn-primary" >back</button>
						<button id="publish_btn" class="btn btn-primary" >publish</button>
					</center>
				</div>
			</div>
		</div>

</div>

<script type="text/javascript" src="javascripts/mine/codemirror.js"></script>
<script type="text/javascript" src="javascripts/mine/xml.js"></script>
<script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="javascripts/mine/search.js"></script>


<script>
	var myTextArea=document.getElementById('htmlInput');
	
	var myCodeMirror = CodeMirror.fromTextArea(myTextArea,{
								  cursorBlinkRate: 550,
								  mode : "xml",
    							  htmlMode: true,
								});


		
//	    jQuery('#htmlContent').html(myCodeMirror.getValue());
	    
//		jQuery('#myModal').modal('show');


	jQuery('#back_btn').click(function(){
		jQuery('#htmlContent').html('');
		jQuery('#myModal').modal('hide');
	});
	
		var login_name="";
		$.ajax({
		       url: 'get_login_nickName',
		       async: false,
		       success: function(data) {
		    	   login_name=eval(data);
		       }
		});
	
	function getUrlVars()
{
    var vars = [], hash;
    var url=decodeURIComponent(window.location.href)
    var hashes = url.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
var para=getUrlVars();

if(para['type']=="create"){
		jQuery('#view_btn').click(function(){
			jQuery.ajax({
		       url: "checkExistence",
		       data: {"name":jQuery('#page_id').val()},
		       success: function(r){
			       if(r==1){
			       		alert("Existed page id.");
			       }else{
			       		publish();	
			       }
	       }
	       });
	   });
}else if(para['type']=="modify"){
		jQuery.ajax({
		       url: "getInfoByPageId",
		       data: {"page_id":para['page_id']
		       },
		       success: function(r){
		       	   r=JSON.parse(r);
			       jQuery('#page_topic_input').val(r['topic']);
			       myCodeMirror.getDoc().setValue(r['code']);
			       
	     	  }
	       });
		
		


		jQuery('#view_btn').click(function(){
			jQuery.ajax({
		       url: "updateByPageId",
		       data: {"page_id":para['page_id'],
		       			 "creator":login_name,
		       			 "new_page_id":transform(login_name+jQuery('#page_topic_input').val()),
		       			 "code":myCodeMirror.getValue(), 
		       			 "url":"http://192.168.0.2:8080/pages/view?page_id="+transform(login_name+jQuery('#page_topic_input').val()),"topic":jQuery('#page_topic_input').val(),
		       			 "topic":jQuery('#page_topic_input').val()
		       },
		       success: function(r){
			       window.location.href="http://192.168.0.2:8080/pages/cp?type=modify&page_id="+transform(login_name+jQuery('#page_topic_input').val())
			       window.open("http://192.168.0.2:8080/pages/view?page_id="+transform(login_name+jQuery('#page_topic_input').val()));
	       }
	       });
	       });

}


	
	
	function transform(s){
		r='';

		for(i= s.length-1; i>=0;i--){
			r+=(s[i].charCodeAt(0)-30).toString();
		}
		return r;
	}
	
	//alert(transform('abc'));
	//alert(login_name);

	function publish(){
				
				
		
				jQuery.ajax({
			       url: "pb",
			       data: {"creator":login_name,"page_id":transform(login_name+jQuery('#page_topic_input').val()),"code":myCodeMirror.getValue(), "url":
			       "http://192.168.0.2:8080/pages/view?page_id="+transform(login_name+jQuery('#page_topic_input').val()),"topic":jQuery('#page_topic_input').val(),
			       },
			       success: function(r){
			       if(r==0){
			       		alert("conflict page name.")
			       }else{
			       		//alert("publish successed. Your page url is \n http://192.168.0.2:8080/pages/view?name="+jQuery('#page_id').val());
						window.location.href="http://192.168.0.2:8080/pages/cp?type=modify&page_id="+transform(login_name+jQuery('#page_topic_input').val())
			       		window.open("http://192.168.0.2:8080/pages/view?page_id="+transform(login_name+jQuery('#page_topic_input').val()));
			       }
			       		
		       }
		       });
	
	}
	

	$('#page_id').focusout(function(){
			jQuery.ajax({
		       url: "checkExistence",
		       data: {"name":jQuery('#page_id').val()},
		       success: function(r){
			       if(r==1){
			       		alert("Existed page id.asa")
			       }
	       }
	       });
	
	});
	
	
	
	
</script>


  </body>
</html>
