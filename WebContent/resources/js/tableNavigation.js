var maleIco = "/EmployeeOrganizer/resources/asset/icon/man.png";
var femaleIco = "/EmployeeOrganizer/resources/asset/icon/woman.png";
var yesIco = "/EmployeeOrganizer/resources/asset/icon/yes.png";
var noIco = "/EmployeeOrganizer/resources/asset/icon/no.png";
var shiftIco = "/EmployeeOrganizer/resources/asset/icon/shifts.png";

$(document).on('click','.employee-shifts',function(){
		hideOtherShifts();
		showEmployeeShift(this);
});

$(document).on('click','.sort', function(){
	sortTable(this);
});

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
	
	$('.status').hover(function(){
        // Hover over code
        var title = $(this).attr('title');
        $(this).data('tipText', title).removeAttr('title');
        $('<p class="tooltip"></p>')
        .text(title)
        .appendTo('body')
        .fadeIn('slow');
		}, function() {
			// Hover out code
			$(this).attr('title', $(this).data('tipText'));
			$('.tooltip').remove();
		}).mousemove(function(e) {
			var mousex = e.pageX + 20; //Get X coordinates
			var mousey = e.pageY + 10; //Get Y coordinates
			$('.tooltip').css({ top: mousey, left: mousex })
		});
	
	});

var desc = false;
function sortTable(el){
	desc = !desc;	
	var criteria = $(el).text() == 'Name' ? 'SurName' : $(el).text();
	$.ajax({
		url: "sort?sort="+criteria+"&desc="+desc,
		dataType: 'json',
		success: function(data){
			parseDataToTable(data);		
			employeeStatus();
		}
	});
}




function parseDataToTable(data){
	var header = $('.employee-head');
	var table = $('#employeesTab');
	table.empty();
	table.append(header);
	
	for(var i = 0; i < data.length; i++){
		var row = $('<tr class="employee-row" data="'+data[i].id+'"></tr>');
		var cellName = $('<td>'+data[i].name+'</td>');
		var cellEmail = $('<td>'+data[i].contact.email+'</td>');
		var cellGender = $('<td><img src="'+(data[i].sex == 'FEMALE' ? femaleIco : maleIco)+'" alt="'+data[i].sex+'"></td>');
		var cellAtWork = $('<td class="status"><img src="'+noIco+'" alt="NO"></td>');
		var shiftRow = $('<tr class="employee-shifts"><td colspan="4"><img src="'+shiftIco+'"></td></tr>');
		
		row.append(cellName);
		row.append(cellEmail);
		row.append(cellGender);
		row.append(cellAtWork);
		
		table.append(row);
		table.append(shiftRow);
	}
}

function hideOtherShifts(){
	$(".shifts-container").slideToggle('slow',function(){
		$(this).parent().parent().remove();
	});
}

function showEmployeeShift(el){
	var id = $(el).prev().attr("data");
	var container = $('<div class="shifts-container" style="display:none"><div>');
	
	if(typeof id != 'undefined'){		
		$(container).load("employeeShifts?id="+id, function(){
			var cell = $('<td colspan="4"></td>');
			var row = $('<tr></tr>').append(cell);
			$(cell).append(container);
			$(el).before(row);
			$(container).slideToggle('slow');
		});		
	}	
}

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
			$(rows).find(".status").find("img").attr("src",noIco);
			$(rows).find(".status").find("img").attr("alt","NO");
			$(rows).find(".status").find("img").attr("title","not present");
			$(rows).each(function(index){
				var rowData = $(this);
				$(data).each(function(index){
					if(data[index].id == rowData.attr("data")){
						var cell = $(rowData).find(".status").find("img");
						$(cell).attr("src",yesIco);
						$(cell).attr("alt","YES");
						$(cell).attr("title",parseDate(data[index].entered));
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
	
	if(hour < 10){
		hour = "0"+hour;
	}
	
	if(minutes < 10){
		minutes = "0"+minutes;
	}
	
	var result = year+"-"+month+"-"+day+" "+hour+":"+minutes;
	return result;
}