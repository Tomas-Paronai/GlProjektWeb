$(document).ready(function(){
	
	/*load employee detail*/
	$(".employee-row").click(function(){
		getEmployeeData($(this).attr("data"));
	});
	
	/*get employee status*/
	employeeStatus();
	setInterval(function(){
		employeeStatus();
	},60000);
	
});

function getEmployeeData(val){
	$.ajax({
		url: "employeeDetail?id="+val,
		dataType: 'json',
		success: function(data){
			$(".cat-value[data=country]").text(data.address.country);
			$(".cat-value[data=city]").text(data.address.city);
			$(".cat-value[data=street]").text(data.address.street);
			$(".cat-value[data=postcode]").text(data.address.postCode);
			$(".cat-value[data=contract]").text(data.detail.contract);
			$(".cat-value[data=position]").text(data.detail.position);
			$(".cat-value[data=salary]").text(data.detail.salary + " $");
			$(".cat-value[data=employed-since]").text(data.detail.workSince);
		}
	});
}

function employeeStatus(){
	$.ajax({
		url: "employeeStatus",
		dataType: 'json',
		success: function(data){
			var rows = $(".employee-row");
			$(rows).find(".status").find("img").attr("src","/EmployeeOrganizer/resources/asset/icon/no.png");
			$(rows).find(".status").find("img").attr("alt","NO");
			$(rows).each(function(index){
				var rowData = $(this);
				$(data).each(function(index){
					if(data[index].id == rowData.attr("data")){
						$(rowData).find(".status").find("img").attr("src","/EmployeeOrganizer/resources/asset/icon/yes.png");
						$(rowData).find(".status").find("img").attr("alt","YES");
					}
				});
			});				
		}
	});
}

function parseDate(time){
	var d = new Date(time);
	var year = d.getFullYear();
	var month = parseInt(d.getMonth())+1;
	var day = d.getDate();
	var hour = d.getHours();
	var minutes = d.getMinutes();
	
	if(month < 10){
		month = "0"+month;
	}
	
	if(minutes < 10){
		minutes = "0"+minutes;
	}
	
	var result = year+"-"+month+"-"+day+" "+hour+":"+minutes;
	return result;
}