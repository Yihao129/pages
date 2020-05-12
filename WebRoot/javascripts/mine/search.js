






$(function(){
	//check login status
	login_status=0
	
	$.ajax({
	       url: 'check_login_status',
	       async: false,
	       success: function(data) {
	    	   login_status=data;
	       }
	});
	


	
	name='yyh';
	
	
	if(login_status==1){
		
		$.ajax({
		       url: 'get_login_nickName',
		       async: false,
		       success: function(data) {
		    	   $("#login_name").html(eval(data));
		       }
		});
		
		$("#login_text").html("logout");
		$("#login_href").attr('href',"logout");
	}else{
		$("#login_text").html("login");
		$("#login_href").attr('href',"login.do");
	}
	
	
	
	
});