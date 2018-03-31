$(document).ready(function(){                            // по завершению загрузки страницы
    $("#book").bind("click", function list() {
        var base_url = 'http://localhost:8080/book/list/';
        console.log($("h1").html().substring(6));
        $.ajax({
            type: "GET",
            url: base_url + $("h1").html().substring(6),
            success: function(response) {
                var json_obj = response;//parse JSON
                $('#bookTable').html('');
                $("#hide").bind("click", function hideBookTable() {
                    if ( $( "#bookTable" ).is( ":hidden" ) ) {
                        $( "#bookTable" ).show( "slow" );
                      } else {
                        $( "#bookTable" ).slideUp();
                      }
                                    });
                for(var i = 0; i < json_obj.length; i++) {
                    $("#bookTable").append("<tr>" + "<td>" + "Name: " + "</td>" + "<td>" + json_obj[i].book.name + "</td>" + "</tr>")
                                   .append("<tr>" + "<td>" + "Author: " + "</td>" + "<td>" + json_obj[i].book.author + "</td>" + "</tr>")
                                   .append("<tr>" + "<td>" + "Issue and return date: " + "</td>" + "<td>" + new Date(json_obj[i].issueDate) + " -> " + new Date(json_obj[i].returnDate) + "</td>" + "</tr>");
                }
            },
            dataType: "json"
        });
    });
    $("#searchBook").bind("click", function searchBook() {
        $("#foundBooks").empty();
        var base_url = 'http://localhost:8080/book/search/';
        $.ajax({
            type: "GET",
            url: base_url + $("#textArea").val(),
            success: function(response) {
                var json_obj = response;
                $("#foundBooks").html('');
                for(var i = 0; i < json_obj.length; i++) {
                    $("#foundBooks").append("<tr>" + "<td>" + "Name: " + "</td>" + "<td>" + json_obj[i].name + "</td>" + "</tr>")
                                    .append("<tr>" + "<td>" + "Author: " + "</td>" + "<td>" + json_obj[i].author + "</td>" + "</tr>");

                }
            },
            dataType: "json"
        });
    });

});




