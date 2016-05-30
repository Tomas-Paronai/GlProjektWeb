$(function(){
	$(".openDialog").on('click', function(event){
		$("#dialog").load($(this).attr("data"), initDialog());
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
	});
	console.log("opening");
	$(template).dialog('open');
	console.log(template);
}

function showEditField(el){
	var parent = $(el).parent();
	$(parent).hide();
	$(parent).next().show();
}

function saveEdit(el){
	var parent = $(el).parent();
	$(parent).hide();
	$(parent).prev().show();
}