var maleIco = "/EmployeeOrganizer/resources/asset/icon/man.png";
var femaleIco = "/EmployeeOrganizer/resources/asset/icon/woman.png";
var yesIco = "/EmployeeOrganizer/resources/asset/icon/yes.png";
var noIco = "/EmployeeOrganizer/resources/asset/icon/no.png";
var shiftIco = "/EmployeeOrganizer/resources/asset/icon/shifts.png";
var delIco = "/EmployeeOrganizer/resources/asset/icon/rubbish-bin.png";

var desc = false;
var searchWord = "";
var criteria = "";
var del = false;
var def = false;

$(document).on('click','.employee-shifts',function(){
		hideOtherShifts();
		showEmployeeShift(this);
});

$(document).on('click','.sort', function(){
	sortTable(this);
});

$(document).on('keypress','.search', function(e){
	searchWord = $(this).val();
	if(e.which == 13){
		sortTable(null);
	}
	setTimeout(function(){
		sortTable(null);		
	},3000);
});

$(document).on('click','.delete-emp', function(){
	if(!def){
		deleteEmployee(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);
	
});

/*load employee detail*/
$(document).on('click', '.employee-row', function(){
	if(!del){
		getEmployeeData($(this).attr("data"));
	}
	setTimeout(function(){
		del = false;
	},500);
});

/*status*/
$(document).on('hover', '.status', function(){
	var title = $(this).attr('title');
    $(this).data('tipText', title).removeAttr('title');
    $('<p class="tooltip"></p>')
    .text(title)
    .appendTo('body')
    .fadeIn('slow');
}, function(){
	$(this).attr('title', $(this).data('tipText'));
	$('.tooltip').remove();
});

$(document).on('mousemove','.tooltip', function(e){
	var mousex = e.pageX + 20; //Get X coordinates
	var mousey = e.pageY + 10; //Get Y coordinates
	$('.tooltip').css({ top: mousey, left: mousex });
});


$(document).ready(function(){	
	
	/*get employee status*/
	employeeStatus();
	setInterval(function(){
		employeeStatus();
	},60000);
	
	});
 
function deleteEmployee(el){
	var id = $(el).parent().attr('data');
	console.log("delete: "+id);
	$.ajax({
		url: "deleteEmployee?id="+id,
		dataType : 'text',
        processData : false,
        contentType : false,
		success: function(){
			deleteRow(id);
		}
	});
}

function deleteRow(el){
	var row = $('.employee-row[data='+el+']');
	var shiftRow = row.next();
	console.log("deleting "+row+" "+shiftRow);
	row.slideUp('slow',function(){
		row.remove();
		shiftRow.remove();
	});	
}

function sortTable(el){	
	console.log(searchWord);
	if(el != null){
		if(criteria == $(el).text()){
			desc = !desc;
		}
		criteria = $(el).text() == 'Name' ? 'SurName' : $(el).text();
	}
	
	var urlCall = "sort?sort="+criteria+"&desc="+desc;
	if(searchWord != ""){
		urlCall +="&search="+searchWord;
	}
	
	$.ajax({
		url: urlCall,
		dataType: 'json',
		success: function(data){
			parseDataToTable(data);		
			employeeStatus();
		}
	});
}

function searchTable(el){	
	searchWord = $(el).val();
	console.log("sreach phrase: "+searchWord);
	sortTable(null);
}

function parseDataToTable(data){
	
	var table = $('#employeesTab');
	table.empty();
	
	
	for(var i = 0; i < data.length; i++){
		var row = $('<tr class="employee-row" data="'+data[i].id+'"></tr>');
		var cellName = $('<td>'+data[i].name+'</td>');
		var cellEmail = $('<td>'+data[i].contact.email+'</td>');
		var cellGender = $('<td><img src="'+(data[i].sex == 'FEMALE' ? femaleIco : maleIco)+'" alt="'+data[i].sex+'"></td>');
		var cellAtWork = $('<td class="status"><img src="'+noIco+'" alt="NO"></td>');
		var cellDelete = $('<td><img class="delete-emp" src="'+delIco+'" alt="delete"></td>');
		var shiftRow = $('<tr class="employee-shifts"><td colspan="4"><img src="'+shiftIco+'"></td></tr>');
		
		row.append(cellName);
		row.append(cellEmail);
		row.append(cellGender);
		row.append(cellAtWork);
		row.append(cellDelete);
		
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
			var cell = $('<td colspan="5"></td>');
			var row = $('<tr></tr>').append(cell);
			$(cell).append(container);
			$(el).before(row);
			$(container).slideToggle('slow');
		});		
	}	
}

function getEmployeeData(val){
	if(val == null){
		$(".cat-value[data=country]").text('-');
		$(".cat-value[data=city]").text('-');
		$(".cat-value[data=street]").text('-');
		$(".cat-value[data=postcode]").text('-');
		$(".cat-value[data=contract]").text('-');
		$(".cat-value[data=position]").text('-');
		$(".cat-value[data=salary]").text('-' + " $");
		$(".cat-value[data=employed-since]").text('-');
	}
	else{
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