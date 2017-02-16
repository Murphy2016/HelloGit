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
			$("#startdate").val(d);
			$("#enddate").val(d);
		    var picker1 = $('#datetimepicker1').datetimepicker({  
		        format: 'YYYY-MM-DD',  
		        locale: moment.locale('zh-cn')
		        
		        //minDate: '2016-7-1'  
		    });  
		    var picker2 = $('#datetimepicker2').datetimepicker({  
		        format: 'YYYY-MM-DD',  
		        locale: moment.locale('zh-cn')
		        
		    }); 
		   
		   
		    //动态设置最小值  
		    picker1.on('dp.change', function (e) {  
		        picker2.data('DateTimePicker').minDate(e.date);  
		    });  
		    //动态设置最大值  
		    picker2.on('dp.change', function (e) {  
		        picker1.data('DateTimePicker').maxDate(e.date);  
		    });  
		});  