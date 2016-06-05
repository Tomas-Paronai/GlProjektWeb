$(function(){
	$(".openDialog").on('click', function(event){
		$('#dialog').empty();
		$("#dialog").load($(this).attr("data"), initDialog());
	});
	
	$(".newBut").click(function(){
		if(!def){
			newItemField(this);
			def = true;
		}
		setTimeout(function(){
			def = false;
		},300);		
	});
	
});

var def;
$(document).on('click','.editBut', function(){
	showEditField(this);
});

$(document).on('click','.cancelBut', function(){
	if(!def){
		hideEditField(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);		
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

$(document).on('click','.select-all',function(){
	if(!def){
		selectCheckboxes(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);
});


$(document).on('click','.employees-select', function(){
	if(!def){
		
		if($('.checkbox-list-container').hasClass('shown')){
			console.log("hideCheckboxes");
			hideCheckboxes();			
		}
		else{
			console.log("showCheckboxes");
			showCheckboxes(this);
		}
		
		def = true;
	}	
	setTimeout(function(){
		def = false;
	},300);	
	
});

$(document).on('submit','export-form', function(){
	$.ajax({
		url: "export",
		success: function(){
			console.log("Exporting...");
		}
	});
});

function showCheckboxes(el){
	var container = $('<div class="checkbox-list-container" style="display:none"></div>');
	
	$(container).load('getEmployees', function(){
		var cell = $('<td colspan="2"></td>');
		var row = $('<tr></tr>').append(cell);
		$(cell).append(container);
		$(el).before(row);
		$(container).slideDown('slow', function(){
			$(this).addClass('shown');
		});
	});
}

function hideCheckboxes(){
	$(".checkbox-list-container").slideUp('slow',function(){
		$(this).parent().parent().remove();
	});
}

function selectCheckboxes(el){
	console.log($(el).is(':checked'));
	if($(el).is(':checked')){
		console.log("unckecking");
		$(el).prop('checked',false);
		$('.list-checkbox').prop('checked',false);
	}
	else{
		console.log("checking");
		$(el).prop('checked',true);
		$('.list-checkbox').prop('checked',true);
	}	
	
}

function initDialog(){
	var di = $("#dialog").dialog({
		width: '500px',
		height: 'auto',
		resizable: false,
		modal: true,
		open: function(){
			console.log("open");
		}
	});
	$(di).dialog("open");
}

function showEditField(el){
	var parent = $(el).parent();
	$(parent).slideUp('fast');
	$(parent).next().slideDown('fast');
}

function hideEditField(el){
	var parent = $(el).parent();
	console.log($('li[class=new]'));
	if($('li').hasClass('new') === true){
		$(parent).slideUp('fast', function(){
			
			var newEl = $('li[class=new]');
			newEl.each(function(index){
				if(isDescendant(newEl[index], el)){
					newEl[index].remove();
					return;
				}
			});
			
		});		
	}
	else{
		$(parent).slideUp('fast');
		$(parent).prev().slideDown('fast');
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
				$(parent).parent().remove();
			},
			error: function(jqXHR, textStatus, errorThrown){
				dialogAjaxError(jqXHR, textStatus, errorThrown);
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
		},
		error: function(jqXHR, textStatus, errorThrown){
			dialogAjaxError(jqXHR, textStatus, errorThrown);
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
		},
		error: function(jqXHR, textStatus, errorThrown){
			dialogAjaxError(jqXHR, textStatus, errorThrown);
		}
	});
}

function dialogAjaxError(jqXHR, textStatus, errorThrown){
	if(errorThrown == 'Internal Server Error'){
		alert("Server is unavailable");
	}
	//$().toastmessage('showNoticeToast', 'Error: '+errorThrown);
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
	$(line).addClass("new");
	$(el).parent().before(line);
}

function isDescendant(parent, child) {
    var node = child.parentNode;
    while (node != null) {
        if (node == parent) {
            return true;
        }
        node = node.parentNode;
    }
    return false;
}