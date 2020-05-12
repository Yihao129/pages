

$(function(){
	$('#prop').html('');
	$('#btn_submit').click(function(){
		$('#btn_submit').prop('disabled', true);
		$('#msg').html('<p style="color:blue;text-align:center;font-size:20px;margin-top:13px;">login..</p>');
		if($('#email').val()!=null && $('#email').val()!='' && $('#pwd').val()!=null && $('#pwd').val()!=''){
				
			$.ajax({
				url:'login',
				type:'post',
				data:{
					email:$('#email').val(),
					password:$('#pwd').val()
				},
				beforeSend:function(){
							
				},
				success:function(r){
					if(r==1){
						location.href="main";
						
					}else{
						$('#msg').html('<p style="color:blue;text-align:center;font-size:20px;margin-top:13px;">wrong password or phone number</p>');
					}
			
				}
				
				
			});
		}else{
			$('#msg').html('<p style="color:blue;text-align:center;font-size:20px;margin-top:13px;">Input can not be null,try again</p>');
		}
		$('#btn_submit').prop('disabled', false);
	});
	
	
	

	
	$('#btn_register2').click(function(){
		$('#btn_register2').prop('disabled', true);
		
		var email=$('#emailR').val();
		var pwd=$('#pwdR').val();
		var pwd2=$('#pwd2R').val();
		var name=$('#nameR').val();
		
		if( email=='' || pwd=='' || pwd2=='' || name==''){
			$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">Incomplete , finish it and try again...</p>');
			
		}else if(pwd!=pwd2){
			$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">password not equal...</p>');
			
		}else{
			$('#prop').html('<p style="color:green;text-align:center;">submitting...</p>');
			$.ajax({
				url:'register',
				type:'post',
				data:{
					email:email,
					password:pwd,
					nickName:name,
				},
				success:function(r){
					$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">submitting...</p>');
					if(r==0){
						$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">existed phone...</p>');
					}else{
						$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">success...</p>');
						$('#myModal').modal('hide');
					}
					
				},
				
			});
		}
		$('#btn_register2').prop('disabled', false);
		
	});
	
	//getCheckCode
	$('#getVN').click(function(){
	$('#getVN').prop('disabled', true);
	var phone=$('#phoneR').val();
	if(phone.length>10){
		$.ajax({
			url:'getCheckCode',
			type:'get',
			data:{
				phone:phone,
			},
			success:function(r){
				$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">sending...</p>');
			
			}
		});
	}else{
		$('#prop').html('<p style="color:blue;text-align:center;font-size:20px;">too short for phone number...</p>');
		
	}
	$('#getVN').prop('disabled', false);
	
});
	
	

	
	
	
});