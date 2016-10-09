$(document).ready(function() {
        $('#word').blur(function(event) {
                var word = $('#word').val();
                $.get('JqueryServlet', {
                        word : word
                }, function(responseText) {
                        $('#ajaxResponse').text(responseText);
                });
        });
});