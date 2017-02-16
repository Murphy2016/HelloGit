/**
 * 
 */

		function myformatter(date){  
		    var y = date.getFullYear();  
		    var m = date.getMonth()+1;  
		    var d = date.getDate();  
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
		}
		$(function () {
			var d = myformatter(new Date());
			//alert(d);
			$("#searchdate").val(d);
			
		    var picker1 = $('#datetimepicker').datetimepicker({  
		        format: 'YYYY-MM-DD',  
		        locale: moment.locale('zh-cn'),
		        
		        //minDate: '2016-7-1'  
		    });  
		    
		   
		   
		  
		});  