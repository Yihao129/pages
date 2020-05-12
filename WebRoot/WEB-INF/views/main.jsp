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
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table.min.css">
    <link rel="SHORTCUT ICON" href="https://cdn1.iconfinder.com/data/icons/files-bolder/512/file_document_new_blank-512.png"/>
    <title >pages</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, 
                                     initial-scale=1.0, 
                                     maximum-scale=1.0, 
                                     user-scalable=no">
                                     
	<link rel="stylesheet" type="text/css" href="css/mine/search.css">

  </head>
  
  <body id="body" background="" style="background-attachment: fixed;background-size: 100%">
    
	<div class="navbar navbar-light navbar-fixed-top" style="background:white;" >
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" style="font-weight:bold;cursor: pointer;" >pages</a>
			
			</div>
		<div class="collapse navbar-collapse" id="myCollapse">
			<ul class="nav navbar-nav navbar-right" style="margin-top:0;">
				<li class="" style="width:150px;text-align:center"><a id="login_name"> </a></li>
				<li class="active"><a id="login_href" href="login.do"><span class="glyphicon glyphicon-home"></span> <span id="login_text">login</span></a></li>
			</ul>	
		</div>
		
		</div>
	</div>
	<hr style="margin-top:50px;"/>
	

<table id="my_pages_table" class="table table-dark" >
  <thead>
    <tr>
      <th scope="col" data-field="topic">topic</th>
      <th scope="col" data-field="url">url</th>
      <th scope="col" data-field="edit">edit</th>
    </tr>
  </thead>
  <tbody>
  
  </tbody>
</table>
	
	<div id="info_plat" style="margin-top:100px;">
		<center><h1>Hi, this website makes you create your pages super easy. After your creation, everyone can access your page through the generated link. Press the login button to begin your creation.</h1></center>
	</div>
	
	
	<div class="navbar navbar-light navbar-fixed-bottom" >
		
		<center style="margin-top:6px;">  

		  <button id='new_page_btn' class="btn btn-primary" style="margin-right:2px;" data-toggle="modal" data-target="#page_info_modal">new page</button>
		</center>
		<view id='show_place'>


		</view>
		
	</div>
	
    <div id="delete_modal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button id="delete_yes_btn" type="button" class="btn btn-primary">yes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">cancel</button>
      </div>
    </div>
  </div>
</div>

    
    

    
    <script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap.min.js"></script>	
	<script type="text/javascript" src="javascripts/bootstrap/bootstrap-table.js"></script>	
	<script type="text/javascript" src="javascripts/mine/search.js"></script>
  </body>

<script>
$('#new_page_btn').click(function(){
	window.location.href="cp?type=create";
});

var login_name="";
$.ajax({
       url: 'get_login_nickName',
       async: false,
       success: function(data) {
    	   login_name=eval(data);
       }
});

if(login_name!=""){
	$('#my_pages_table').show();
	$('#new_page_btn').show();
	$('#info_plat').hide();
	$.ajax({
		       url: "getInfoByCreator",
		       data:{"creator":login_name},
		       success: function(r){
		       		r=$.parseJSON(r)
		       		r.reverse()
		       		
		       		for(i in r){
		       			r[i]['edit']="<a style='cursor: pointer;' onclick=\"delete_clicked(\'"+r[i]['page_id']+"\');\">delete</a> <a style='cursor: pointer;' href='cp?type=modify&page_id="+r[i]['page_id']+"'>modify</a>"
		       			r[i]['url']="<a target='_blank' href='"+r[i]['url']+"'>"+r[i]['url']+"</a>";
		       			console.log(r[i]['url']);
		       		}
		       		
		       		
		       		$('#my_pages_table').bootstrapTable({
				        data: r
				    });
		       }
		       })
}else{
	$('#my_pages_table').hide();
	$('#new_page_btn').hide();
	$('#info_plat').show();
}


function delete_clicked(e){

	$('#delete_modal').modal('show');
	
	$('#delete_yes_btn').click(function(){
			$.ajax({
		       url: "deleteByPageId",
		       data:{"page_id":e.toString()},
		       success:function(r){
		       		window.location.href="http://192.168.0.2:8080/pages/main";
		       }
		     });
	
	});
	
	

		     
		     
}


</script>

</html>


