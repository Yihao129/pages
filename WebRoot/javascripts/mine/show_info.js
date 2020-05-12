$(function(){
	$('#table').datagrid({
		width:400,
		title:'user info',
		url:'getInfo2',
		toolbar:$('#tb'),
		columns:[[
			
			{
				field :'user',
				title :'user',
				//sortable:true,
			},
			{
				field :'password',
				title :'password',
				//sortable:true,
			}
			
			
		]],	
		pagination:true,
		pageSize:10,
		pageList:[10,20,30],
		sortName:'user',
		sortOrder:'desc',
		
			
		});
	//console.log('as');

//	$.ajax({
//		url:'getInfo',
//		type:'get',
//		success:function(){
//			var data="[{'user':'11','password':'123'}]";
//			alert(data+"1");
//			$.parseJSON(data);
//			alert(data);
//			
//			$('#table').datagrid('loadData',data);
//		}
		
//	});
	$('#inputUser').validatebox({
		required:true,
	});
	
	$('#inputPassword').validatebox({
		required:true,
	});
			
			
	$('#insertDialog').dialog({
		width:300,
		height:190,
		closed:true,
		title:'regist a new user here',
		buttons:[{
			text:'submit',
			width:80,
			onClick:function(){
				//alert('');
				if(!$('#inputUser').validatebox('isValid')){
					$('#inputUser').focus();
				}else if(!$('#inputPassword').validatebox('isValid')){
					$('#inputPassword').focus();
				}else{
					//alert('in');
					$.ajax({
						url:'regesterUser',
						type:'post',
						data:{
							user:$('#inputUser').val(),
							password:$('#inputPassword').val()
						},
						success:function(data){
							if(data==0){
								$.messager.show({
									title:'tips',
									msg:'name already existed,try again',
									timeout:2000,
									showType:'slide'
									
								});
								$('#form').form('clear');
							}else{
								alert('success');
								$('#table').datagrid('reload');
								$('#insertDialog').dialog('close');
							}
						}
					});
					
				}
			},
				
			
			
		},{
			text:'cancel',
			width:80,
			onClick:function(){
				$('#insertDialog').dialog('close');
				
			}
			
		}],
		onOpen:function(){
			$('#form').form('clear');
		},
	});
	
	
	
	$('#inputUser2').validatebox({
		required:true,
	});
	$('#inputPassword2').validatebox({
		required:true,
		
	});
	
	
	
	
	
	$('#updateDialog').dialog({
		onBeforeOpen:function(){
			
		},
		title:'update ',
		width:300,
		closed:true,
		onOpen:function(){
			$('#form2').form('clear');
		},
		buttons:[{
			text:'submit',
			width:80,
			onClick:function(){
				if(!$('#inputUser2').validatebox('isValid')){
					$.messager.show({
						title:'tips',
						content:'input a user name',
						timeout:800
					});
					$('#inputUser2').focus();
				}else if(!$('#inputPassword2').validatebox('isValid')){
					$.messager.show({
						title:'tips',
						content:'input a password',
						timeout:800
					});
					$('#inputPassword2').focus();
					
				}else{
					var t=$('#table').datagrid('getSelections');
					$.ajax({
						url:'updateUserInfo',
						type:'post',
						data:{
							user:$('#inputUser2').val(),
							password:$('#inputPassword2').val(),
							oldUser:t[0].user,
							
						},
						success:function(r){
							if(r==1){
								$.messager.show({
									title:'tips',
									content:'update success',
									timeout:800
									
								});
								$('#updateDialog').dialog('close');
								$('#table').datagrid('reload');
							}else{
								alert('fail');
							}
							
						}
					});
					
				}
				
			},
			
		},{
			text:'cancel',
			width:80,
			onClick:function(){
				$('#updateDialog').dialog('close');
				$('#table').datagrid('reload');
			},
		}],
		
	});
	
	
	
	
	
	//$('#tb').toolbar();
	function_set={
		add:function(){
			$('#insertDialog').dialog('open');
			
		},
		update:function(){
			var temp=$('#table').datagrid('getSelections');
			if(temp.length==1){
				$.ajax({
					url:'check_login',
					type:'post',
					data:{
						user:temp[0].user,
						password:temp[0].password,
					},
					success:function(r){
						if(r==1){
						$('#updateDialog').dialog('open');
						}else{
							$.messager.show({
								title:'tips',
								content:'no content finding',
								timeout:800
							});
						}
					}
					
				});
				
				//alert(temp[0].user);
			}else{
				$.messager.show({
					title:'tips',
					content:'select one content to update',
					timeout:800,
				});
			}
			
		},
		
		
		deleteUser:function(){
			var nums=$('#table').datagrid('getSelections');
			alert(nums);
			var names=[];
			for(var i=0;i<nums.length;i++){
				names.push(nums[i].user);
			}
			$.ajax({
				url:'deleteUser',
				type:'post',
				data:{
					users:names.join(',')
				},
				success:function(){
					alert('sucess');
					
				}
				
			});
		}
		
	};
	
	
	
	
});