$(document).ready(function() {
	$('#userName').blur(function() {
		$.ajax({
			url : 'JqueryServlet',
			data : {
				userName : $('#userName').val()
			},
			success : function(responseText) {
				$('#ajaxGetUserServletResponse').text(responseText);
			}
		});
	});
});