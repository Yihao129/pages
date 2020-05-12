$(function(){

	var v1=Math.floor(Math.random()*100+1);    //�����������ֵ
	var v2=Math.floor(Math.random()*100+1);
	
	$.ajax({
		url:'getShowMsg',
		success:function(str){
			var json=JSON.parse(str);
			$('#signCount').html(json.signCount);
			$('#seriesCount').html(json.seriesCount);
			$('#points').html(json.points);
		}
		
	});
	
	
	
	
	$('#login_out').click(function(){
		$.ajax({
			url:'logout',
		});
		$('#msg').html('<p style="color:blue;text-align:center;font-size:20px;margin-top:13px;">退出登录</p>');
	});
	
	
	$('#clear').click(function(){
		$('#msg').html('');
		
	});

	$('#msg').html('<p>wakewake ...</p>');
	
	$('#btn_sign').click(function(){
		$('#msg').html('<p>签到中...</p>');
		$('#btn_sign').prop('disabled', true);
		$.ajax({                                    //���ǩ��ʱ��
			url:'check_sign_time',
			success:function(r){
				if(r==0){
					$('#msg').html('<p style="color:blue;text-align:center;font-size:20px;margin-top:13px;">失败: 在 6:00-7:20 签到</p>');
					$('#btn_sign').prop('disabled', false);
				}else{								//ʱ��ϸ�ִ��
					$.ajax({                               //��鵱���Ƿ�ǩ��
						url:'check_is_signed',
						success:function(r){
							if(r==1){
								$('#msg').html('<p>签过了...</p>');
								$('#btn_sign').prop('disabled', false);
							}else if(r==3){
								$('#msg').html('<p>过期，请重新登陆...</p>');
								window.location.href='/wake_up';
								$('#btn_sign').prop('disabled', false);
								}else{                                       //����ûǩ��ִ��
									$('#msg').html('<p>先回答下面的问题 ...</p>');
									$('#alr_div').attr('style','display:block;');
									v1=Math.floor(Math.random()*100+1);
									v2=Math.floor(Math.random()*10+1);
									$('#alr_show').html(v1+' * '+v2+' = ');		
								}
						}
					});
				}
			}	
		});
	});
	
	
	$('#one_more_time').click(function(){                             //点击换一道题目
		$('#msg').html('<p>变了...</p>');
		$('#alr_div').attr('style','display:block;');
		$('#alr_result').val('');
		v1=Math.floor(Math.random()*100+1);
		v2=Math.floor(Math.random()*10+1);
		$('#alr_show').html(v1+' * '+v2+' = ');	
	});
	
	
	$('#submitIt').click(function(){                                          //点击提交检查计算结果
		var r=$('#alr_result').val();
		if(r=='' || r==null){
			$('#msg').html('<p>请先填写答案 ...</p>');
		}else{
			$('#msg').html('<p>测试答案中 ...</p>');
			$.ajax({
				url:'check_alr_answer',
				data:{
					v1:v1,
					v2:v2,
					answer:r,
				},
				success:function(r){
					if(r==0){
						$('#msg').html('<p style="color:red;">答错了，再试一次</p>');
					}else if(r==1){                                                                         //答对了，进行签到成功处理
						$('#msg').html('<p style="color:green;">答对了 ...</p>');
						$.ajax({
							url:'signIn',
							success:function(r2){
								if(r2==1){                                   //更改签到次数
									location.reload();
								}else{
									$('#msg').html('<p style="color:green;">服务器内部原因导致签到失败 ...</p>');
								}
							}
						});
					}
				}
			});
		}
	});
	
	
	
	$.ajax({
		url:'if_today_has_signed',
		success:function(r){
			if(r==1){
				$.ajax({
					url:'getTodayPoints',
					success:function(points){
						$('#msg').html('<p style="color:green;">今天已签到,获得'+points+'积分</p>');
					}
				});
				
			}
		}	
	});
	
	
	
	
	
	 $.ajax({                                                         //初始化今日信息表
	       url: 'getTodySignInfo',
	       dataType: 'json',
	       success: function(data) {
	    	  
	           $('#myTable').bootstrapTable({
	        	  classes:'default',
	        	  sortbale:true,
	        	  sortName: "time",
	        	
	              data :data,
	              columns:[{
	            	  field:'index',  
	            	  formatter: function (value, row, index) {
                          return index+1;
                      }
	              },{
	            	  field:'name',
	              },{
	            	  field:'time',
	              },{
	            	  field:'series_count',
	              },{
	            	  field:'accumulate_point',
	              }],
	             
	              
	           });
	       },
	       error: function(e) {
	           console.log(e.responseText);
	       }
	    });
	
	 
	 $('#myTable').on('search.bs.table', function (e, text) {
	        console.log('Event: search.bs.table');
	    });
	 
	 
	
//	 $(function () {
//		    var $result = $('#eventsResult');
//
//		    $('#eventsTable').on('all.bs.table', function (e, name, args) {
//		        console.log('Event:', name, ', data:', args);
//		    })
//		    .on('click-row.bs.table', function (e, row, $element) {
//		        $result.text('Event: click-row.bs.table');
//		    })
//		    .on('dbl-click-row.bs.table', function (e, row, $element) {
//		        $result.text('Event: dbl-click-row.bs.table');
//		    })
//		    .on('sort.bs.table', function (e, name, order) {
//		        $result.text('Event: sort.bs.table');
//		    })
//		    .on('check.bs.table', function (e, row) {
//		        $result.text('Event: check.bs.table');
//		    })
//		    .on('uncheck.bs.table', function (e, row) {
//		        $result.text('Event: uncheck.bs.table');
//		    })
//		    .on('check-all.bs.table', function (e) {
//		        $result.text('Event: check-all.bs.table');
//		    })
//		    .on('uncheck-all.bs.table', function (e) {
//		        $result.text('Event: uncheck-all.bs.table');
//		    })
//		    .on('load-success.bs.table', function (e, data) {
//		        $result.text('Event: load-success.bs.table');
//		    })
//		    .on('load-error.bs.table', function (e, status) {
//		        $result.text('Event: load-error.bs.table');
//		    })
//		    .on('column-switch.bs.table', function (e, field, checked) {
//		        $result.text('Event: column-switch.bs.table');
//		    })
//		    .on('page-change.bs.table', function (e, number, size) {
//		        $result.text('Event: page-change.bs.table');
//		    })
//		    .on('search.bs.table', function (e, text) {
//		        $result.text('Event: search.bs.table');
//		    });
//		});
	 
	
});

