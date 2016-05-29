$(document).ready(function(){
	
	$(".employee-row").click(function(){
		$(".detail").css("display","none");
		$(".detail:gt(" + $(this).index() + ")").css("display","block");
	});
	
});