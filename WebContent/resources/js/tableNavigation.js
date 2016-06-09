var maleIco = "/EmployeeOrganizer/resources/asset/icon/man.png";
var femaleIco = "/EmployeeOrganizer/resources/asset/icon/woman.png";
var yesIco = "/EmployeeOrganizer/resources/asset/icon/yes.png";
var noIco = "/EmployeeOrganizer/resources/asset/icon/no.png";
var shiftIco = "/EmployeeOrganizer/resources/asset/icon/shifts.png";
var delIco = "/EmployeeOrganizer/resources/asset/icon/deleteEmployee.png";
var editIco = "/EmployeeOrganizer/resources/asset/icon/edit-icon.png";

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

var toggled = false;
var currLine = null;
var prevLine = null;
$(document).on('click','.edit-emp',function(){
	parent = $(this).closest('.employee-row');
	if(prevLine == null){
		prevLine = currLine = parent;
	}
	else{
		currLine = parent;
	}
	
	if(prevLine.attr('data') == currLine.attr('data')){
		toggle(parent);
	}
	else{
		hideAndSave(prevLine);
		showCurrLine(currLine);
		prevLine = currLine;
	}
});

function hideAndSave(prev){
	saveUpdate(prev)
	$(prev).find('.edit-table').hide();
	$(prev).find('.view-table').show();
}

function showCurrLine(curr){
	$(curr).find('.edit-table').show();
	$(curr).find('.view-table').hide();
	
	$('.edit').show();
	$('.view').hide();
}

function saveUpdate(el){
	if(el != null){
		console.log("saving changes");
		var elData = {};
		elData['id'] = $(el).attr('data');
		elData['firstName'] = $(el).find('input[name=firstname]').val();
		elData['lastName'] = $(el).find('input[name=lastname]').val();
		elData['email'] = $(el).find('input[name=email]').val();
		elData['sex'] = $(el).find('select[name=sex]').val();
		elData['dob'] = $('.edit').find('input[data=dob]').val();
		elData['phone'] = $('.edit').find('input[data=phone]').val();
		elData['country'] = $('.edit').find('input[data=country]').val();
		elData['city'] = $('.edit').find('input[data=city]').val();
		elData['street'] = $('.edit').find('input[data=street]').val();
		elData['postcode'] = $('.edit').find('input[data=postcode]').val();
		elData['position'] = $('.edit').find('select[data=position]').val();
		elData['contract'] = $('.edit').find('select[data=contract]').val();
		elData['salary'] = $('.edit').find('input[data=salary]').val();
		elData['employedsince'] = $('.edit').find('input[data=employed-since]').val();
		
		console.log(elData);
		console.log(elData['id']);
		console.log(elData['firstName']);
		console.log(elData['lastName']);
		console.log(elData['email']);
		console.log(elData['sex']);
		console.log(elData['dob']);
		console.log(elData['phone']);
		console.log(elData['country']);
		console.log(elData['city']);
		console.log(elData['street']);
		console.log(elData['postcode']);
		console.log(elData['position']);
		console.log(elData['contract']);
		console.log(elData['salary']);
		console.log(elData['employedsince']);
		
		$.ajax({
			type: 'POST',
			contentType: "application/json",
			url: 'updateEmployee',
			data: JSON.stringify(elData),
            dataType: 'text',
            processData : false,
            success: function(){
            	sortTable(null);
            	getEmployeeData(elData['id']);
            },
            error: function(xhr, status, error){
            	console.log(xhr+ " && " +status+" && "+error);
            }
		});
	}
}

function toggle(el){
	if(toggled == false){
		console.log("Showing "+el.attr('data'));
		toggled = true;
		$(el).find('.edit-table').show();
		$(el).find('.view-table').hide();
		
		$('.edit').show();
		$('.view').hide();
	}
	else{
		toggled = false;
		console.log("Hiding "+el.attr('data'));
		saveUpdate(el);
		$(el).find('.edit-table').hide();
		$(el).find('.view-table').show();
		
		$('.edit').hide();
		$('.view').show();
	}
}

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
	var id = $(el).parent().parent().attr('data');
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
	var vissibleDiv = row.find("div:visible");
	vissibleDiv.slideUp('slow',function(){
		if($(this).hasClass('edit-table')){
			toggled = false;
			currLine = prevLine = null;
			$('.edit').hide();
			$('.view').show();
		}
		row.remove();
		shiftRow.remove();
	});	
	getEmployeeData(null);
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
		var cellName = $('<td><div class="view-table">'+data[i].name+'</div></td>');
		var cellEmail = $('<td><div class="view-table">'+data[i].contact.email+'</div></td>');
		var cellGender = $('<td><div class="view-table"><img src="'+(data[i].sex == 'FEMALE' ? femaleIco : maleIco)+'" alt="'+data[i].sex+'"></div></td>');
		var cellAtWork = $('<td class="status"><div><img src="'+noIco+'" alt="NO"></div></td>');
		var cellDelete = $('<td><div class="delete-emp"><img src="'+delIco+'" alt="delete"></div><div class="edit-emp"><img src="'+editIco+'" alt="edit"></div></td>');
		var shiftRow = $('<tr class="employee-shifts"><td colspan="5"><img src="'+shiftIco+'"></td></tr>');
		
		var nameInput = $('<div class="edit-table" hidden><input name="firstname" value="'+data[i].firstName+'" size="10"/><input name="lastname" value="'+data[i].lastName+'" size="10"/></div>');
		var emailInput = $('<div class="edit-table" hidden><input name="email" value="'+data[i].contact.email+'"/></div>');
		var sexInput = $('<div class="edit-table" hidden><select name="sex"><option class="female" value="Female">FEMALE</option><option class="male" value="Male">MALE</option></select></div>');
		
		
		cellName.append(nameInput);
		cellEmail.append(emailInput);
		cellGender.append(sexInput);
		
		row.append(cellName);
		row.append(cellEmail);
		row.append(cellGender);
		row.append(cellAtWork);
		row.append(cellDelete);
		
		table.append(row);
		table.append(shiftRow);
		
		
		if(data[i].sex == 'FEMALE'){
			row.find('select[name=sex]').val('Female');
		}
		else{
			row.find('select[name=sex]').val('Male');
		}
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
			getEmployeeData(id);
		});		
	}	
}

function getEmployeeData(val){
	if(val == null){
		$(".cat-value[data=country]").text('-');
		$(".cat-value[data=city]").text('-');
		$(".cat-value[data=street]").text('-');
		$(".cat-value[data=postcode]").text('-');
		$(".cat-value[data=dob]").text('-');
		$(".cat-value[data=phone]").text('-');
		$(".cat-value[data=contract]").text('-');
		$(".cat-value[data=position]").text('-');
		$(".cat-value[data=salary]").text('-' + " $");
		$(".cat-value[data=employed-since]").text('-');
		
		$(".edit-value[data=country]").val('');
		$(".edit-value[data=city]").val('');
		$(".edit-value[data=street]").val('');
		$(".edit-value[data=postcode]").val('');
		$(".edit-value[data=dob]").val('');
		$(".edit-value[data=phone]").val('');
		$(".edit-value[data=salary]").val('');
		$(".edit-value[data=employed-since]").val('');
		
		$(".edit-value[data=position]").val($(".edit-value[data=position] option:first").val());
		$(".edit-value[data=contract]").val($(".edit-value[data=contract] option:first").val());
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
				$(".cat-value[data=dob]").text(data.dob);
				$(".cat-value[data=phone]").text(data.contact.phone);
				$(".cat-value[data=contract]").text(data.detail.contract);
				$(".cat-value[data=position]").text(data.detail.position);
				$(".cat-value[data=salary]").text(data.detail.salary + " $");
				$(".cat-value[data=employed-since]").text(data.detail.workSince);
				
				$(".edit-value[data=country]").val(data.address.country);
				$(".edit-value[data=city]").val(data.address.city);
				$(".edit-value[data=street]").val(data.address.street);
				$(".edit-value[data=postcode]").val(data.address.postCode);
				$(".edit-value[data=dob]").val(data.dob);
				$(".edit-value[data=phone]").val(data.contact.phone);
				$(".edit-value[data=salary]").val(data.detail.salary);
				$(".edit-value[data=employed-since]").val(data.detail.workSince);
				
				$('.edit-value[data=position]').val(data.detail.position);
				$('.edit-value[data=contract]').val(data.detail.contract);
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