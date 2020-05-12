$(function(){
	$('#regist').linkbutton({
		onClick:function(){
			
			window.location.href="goToRegistPage";
		}
		
	});
	$('#logout').click(function(){
		//alert("");
		window.location.href="easy";
		
	});
	
	$('#tabs').tabs();
	
	
	
	//==================================
	$('#tabs').tabs('add',{
		title:'管理员模块',
		closable:true,
		href:'show_info',
	});
	//==================================
	
	
	$('#niv').tree({
		url:'getTree',
		lines:true,
		onClick:function(node){
			if(node.url){
				if($('#tabs').tabs('exists',node.text)){
					$('#tabs').tabs('select',node.text);
				}
				else{
				$('#tabs').tabs('add',{
					title:node.text,
					closable:true,
					href:node.url,
				});
				}
			}
			
		}
		
		
	});
	
});