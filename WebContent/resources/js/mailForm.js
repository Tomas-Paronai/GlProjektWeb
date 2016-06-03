var recipents = new Array();;

var def;
$(document).on('click', '.list-checkbox', function(){
	if(!def){
		updateRecipents(this);
		def = true;
	}
	setTimeout(function(){
		def = false;
	},300);		
});

function updateRecipents(el){
	if(jQuery.inArray($(el).val(),recipents) != -1){
		recipents = jQuery.grep(recipents, function(value){
			return value != $(el).val();
		});
		removeEmail(el);
	}
	else{
		recipents.push($(el).val());
		insertEmail(el);
	}	
}

function removeEmail(el){
	var email = $(el).parent().prev().find('.person-mail').text();
	var mails = $('.recipents-field').val();
	console.log(email);
	console.log(mails);
	mails = mails.replace(email,'');
	mails = mails.replace(';;',';');
	$('.recipents-field').val(mails);
	correctField();
}

function correctField(){
	var mails = $('.recipents-field').val();
	if(mails.charAt(0) == ';'){
		mails = mails.slice(1);
	}
	if(mails.charAt(mails.length - 1) == ';'){
		mails = mails.slice(0,mails.length - 1);
	}
	$('.recipents-field').val(mails);
}

function insertEmail(el){
	var email = $(el).parent().prev().find('.person-mail').text();
	var prev = $('.recipents-field').val();
	if(prev == ''){
		$('.recipents-field').val(prev +=email);
	}
	else{
		$('.recipents-field').val(prev += ";"+email);
	}
}