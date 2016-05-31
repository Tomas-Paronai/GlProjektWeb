$(function(){
	$(".openDialog").on('click', function(event){
		$('#dialog').empty();
		$("#dialog").load($(this).attr("data"), initDialog());
	});
	
	$(".newBut").click(function(){
		newItemField(this);
	});
	
	
});

var def;
$(document).on('click','.editBut', function(){
	showEditField(this);
});

$(document).on('click','.cancelBut', function(){
	hideEditField(this);
});

$(document).on('click','.confirmBut', function(){
	if(!def){
		saveEdit(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);
});

$(document).on('click','.deleteBut', function(){
	if(!def){
		deleteItem(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);	
});

function initDialog(){
	var di = $("#dialog").dialog({
		width: 'auto',
		height: 'auto',
		modal: true,
		open: function(){
			console.log("open");
		}
	});
	$(di).dialog("open");
}

function showEditField(el){
	var parent = $(el).parent();
	$(parent).hide();
	$(parent).next().show();
}

function hideEditField(el){
	var parent = $(el).parent();
	var attr = $(el).parent().attr("data");
	//TODO hide & remove
	if(attr == 'new'){
		$(el).parent().remove();
	}
	else{
		$(parent).hide();
		$(parent).prev().show();
	}	
}

function saveEdit(el){
	var parent = $(el).parent();
	$(parent).hide();
	var parentSibling = $(parent).prev();
	$(parentSibling).show();
	
	var input = $(parent).find("input[name=name]");
	var name = $(input).val();
	var id = $(input).attr("data");
	var table = $("ul[class=items-list]").attr("data");
	
	if(id != 0 && typeof id != 'undefined'){
		updateItem(id,name,table,parentSibling);			
	}
	else{
		if(name != ''){
			insertItem(name, table, parentSibling, parent)
		}
	}
	
}

function deleteItem(el){
	var parent = $(el).parent();
	var parentSibling = $(parent).next();
	var id = $(parentSibling).find("input").attr("data");
	var table = $("ul[class=items-list]").attr("data");
	if(typeof id != 'undefined'){
		$.ajax({
			type: 'POST',
			url: 'deleteItem?i_item='+id+"&table="+table,
			success: function(){
				console.log("Delete was successful");
				//TODO remove line
			}
		});
	}
}

function updateItem(id,name,table,sibling){
	$.ajax({
		type: 'POST',
		url: 'saveItem?i_item='+id+'&d_item='+name+'&table='+table,
		success: function(){
			console.log("Update was successful");
			$(sibling).find("label").text(name);	
		}
	});
}

function insertItem(name,table,sibling,parent){
	$.ajax({
		type: 'POST',
		url: 'insertItem?d_item='+name+'&table='+table,
		success: function(data){
			console.log("Insert was successful");
			$(parent).find("input").attr("data",data.id);
			$(sibling).find("label").text(data.name);
			$(parent).parent().removeAttr("data");
		}
	});
}


function newItemField(el){
	var line = $('<li></li>');
	
	console.log("Inserting show container");
	var showContainer = $('<div class="item-display" hidden></div>');
	$(showContainer).append('<label></label>');
	$(showContainer).append('<img class="editBut" alt="edit" src="/EmployeeOrganizer/resources/asset/icon/edit.png">');
	$(showContainer).append('<img class="deleteBut" alt="delete" src="/EmployeeOrganizer/resources/asset/icon/delete.svg">');
	$(line).append(showContainer);
	
	console.log("Inserting input container");
	var inputContainer = $('<div class="item-edit"></div>');
	$(inputContainer).append('<input name="name"/>');
	$(inputContainer).append('<img class="confirmBut" alt="confirm" src="/EmployeeOrganizer/resources/asset/icon/confirm.png">');
	$(inputContainer).append('<img class="cancelBut" alt="cancel" src="/EmployeeOrganizer/resources/asset/icon/cancel.png">');
	$(line).append(inputContainer);
		
	console.log("Inserting line");
	$(line).attr("data","new");
	$(el).parent().before(line);
}