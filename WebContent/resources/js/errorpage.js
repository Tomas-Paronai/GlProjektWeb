$(document).ready(function(){
	setTimeout(function(){
		window.location.replace($('p').attr('goto'));
	},10000);
})