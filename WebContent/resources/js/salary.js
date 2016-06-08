$(document).ready(function(){
	showEmployeeShifts($('.employee-select').find(":selected"),$('.period').find(":selected").val());
});

$(document).on('change','.employee-select', function(){
	showEmployeeShifts($('.employee-select').find(":selected"),$('.period').find(":selected").val());
});

$(document).on('change','.period', function(){
	showEmployeeShifts($('.employee-select').find(":selected"),$('.period').find(":selected").val());
});

function showEmployeeShifts(emp,period){
	$('.period-container').slideUp('fast', function(){
		$(this).remove();
	});
	
	var container = $('<div class="period-container" style="display:none"><div>');	
	var table = $('<table class="shift-table"></table>');
	var head = $('<tr><th>Enter time</th><th>Exit time</th><th>Hours</th></tr>');
	table.append(head);
	
	$.ajax({
		url: "employeeShiftsPeriod?id="+emp.val()+"&period="+period,
		dataType: 'json',
		success: function(data){
			alert(data.length);
			for(var i = 0; i < data.length; i++){
				var row = $('<tr class="display"></tr>');
				row.append('<td class="enter-time">'+data[i].enterTime+'</td>');
				row.append('<td class="enter-time">'+data[i].exitTime+'</td>');
				row.append('<td class="enter-time">'+data[i].totalHours+'</td>');
				table.append(row);
			}
			var wage = $('.wage-div[emp='+emp.val()+']').text();
			displayHours(wage)
		}
	});
	container.append(table);
	$('.table-container').append(container);
	container.slideDown('fast');
}

function displayHours(wage){
	var rows = $('.shift-table').find('.display');
	var hours = 0;
	rows.each(function(){
		hours += parseInt($(this).find('.shift-time').text());
	});
	
	$('.hours').text(hours + " h");
	$('.wage').text(hours*wage + " $");
}