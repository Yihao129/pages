$(function(){
	 $.ajax({
	       url: 'getRankingInfo',
	       dataType: 'json',
	       success: function(data) {
	           $('#rankingTable').bootstrapTable({
	        	  data :data,
	              columns:[{
	            	  field:'index',  
	            	  formatter: function (value, row, index) {
                        return index+1;
                    }
	              },{
	            	  field:'name',
	              },{
	            	  sortable:true,
	            	  field:'count',	  
	              },{
	            	  sortable:true,
	            	  field:'points'
	              }],
	           });
	       },
	       error: function(e) {
	           console.log(e.responseText);
	       }
	    });
});