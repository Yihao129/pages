$(function(){
	$('#d1').dialog({
		buttons:'#btn1',
		width:290,
		height:200,
		title:'login',
		onOpen:function(){
			$('#form').form('clear');
			
		},
		
	});
	
	$('#i1').validatebox({
		required:true,
		
	});
	
	$('#i2').validatebox({
		required:true,
		
		
	});
	
	if(!$('#i1').validatebox('isValid')){
		$('#i1').focus();
		
	}
	else if(!$('#i2').validatebox('isValid')){
		$('#i2').focus();
		
	}
	
	$('#btn1 a').click(function(){
		//alert("111");
		if(!$('#i1').validatebox('isValid')){
			$('#i1').focus();
			
		}
		else if(!$('#i2').validatebox('isValid')){
			$('#i2').focus();
			
		}
		else{
			//alert("submiting..");
			$.ajax({
				url:'check_login',
				type:'post',
				data:{
					user:$('#i1').val(),
					password:$('#i2').val(),
					
				},
				success:function(data,response,state){
					//alert(data);
					//location.href='http://www.douyu.com';
					
					if(data==1)
					{
						location.href='menu';
						
					}
					else
						{
						
						alert("password or user wrong");
						}
				}
			});
			
			
		}
		
		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});