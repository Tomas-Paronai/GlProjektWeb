$(function(){
	$(".openDialog").on('click', function(event){ //TODO
		$("#dialog").load($(this).attr("href"), initDialog());
	});
	
	$(".editButt").click(function(){
		showEditField(this);
	});
		
	$(".confirmBut").click(function(){
		saveEdit(this);
	});	
});

function initDialog(){
	var template = $("#dialog").dialog({
		width : 'auto',
		height : 'auto',
		modal: true,
		resizable: false,
		draggable: true,
		open: function(){
			console.log("opening");
		}
	});
	$(template).dialog('open');
	$(template).focus();
	console.log(template);
}

function showEditField(el){
	console.log("Show: "+el);
	var parent = $(el).parent();
	$(parent).hide();
	$(parent).next().show();
}

function saveEdit(el){
	console.log("Hide: "+el);
	var parent = $(el).parent();
	$(parent).hide();
	$(parent).prev().show();
}