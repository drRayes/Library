$(document).ready(function(){                          
    $("#hide").hide();
    $("#book").bind("click", function list() {
        var baseUrl = 'http://localhost:8080/book/list/';
        console.log($("h1").html().substring(6));
        $.ajax({
            type: "GET",
            url: baseUrl + $("h1").html().substring(6),
            success: function(response) {
                var jsonObj = response;
                $("#bookTable").html("");
                $("#hide").show();
                $("#hide").bind("click", function hideBookTable() {
                    if ($("#bookTable").is(":hidden")) {
                        $("#bookTable").show("slow");
                        $("#hide").html("Hide");
                    } else {
                        $( "#bookTable" ).slideUp();
                        $("#hide").html("Slide up");
                    }
                })
                $("#bookTable").append("<tr><th>Name</th><th>Author</th><th>Issue and return date</th></tr>");
                for(var i = 0; i < jsonObj.length; i++) {
                    $("#bookTable").append("<tr><td>" + jsonObj[i].book.name + "</td><td>" + jsonObj[i].book.author + 
                    "</td><td>" + new Date(jsonObj[i].issueDate) + " -> " + new Date(jsonObj[i].returnDate) + "</td></tr>");
                }
            },
            dataType: "json"
        });
    });
    $("#searchBook").bind("click", function searchBook() {
        $("#foundBooks").empty();
        var baseUrl = 'http://localhost:8080/book/search/';
        $.ajax({
            type: "GET",
            url: baseUrl + $("#textArea").val(),
            success: function(response) {
                var jsonObj = response;
                $("#foundBooks").html("");
                $("#foundBooks").append("<tr><th>Name</th><th>Author</th></tr>");
                for(var i = 0; i < jsonObj.length; i++) {
                    $("#foundBooks").append("<tr><td>" + jsonObj[i].name + "</td><td>" + jsonObj[i].author + "</td></tr>");
                }
            },
            dataType: "json"
        });
    });

    $("#allBooks").bind("click", function() {
        $("bookTable").empty();
        $("#hide").hide("slow");
        var baseUrl = 'http://localhost:8080/book/allBooks';
        $.ajax({
            type: "GET",
            url: baseUrl,
            success: function(response) {
                var jsonObj = response;
                $("#foundBooks").html("");
                $("#foundBooks").append("<tr><th>Name</th><th>Author</th><th>Description</th></tr>");
                for(var i = 0; i < jsonObj.length; i++) {
                    $("#foundBooks").append("<tr><td>" + jsonObj[i].name + "</td><td>" + jsonObj[i].author + "</td><td>" +
                    jsonObj[i].description +"</td></tr>");
                }
            },
            dataType: "json"
        });
    })

});




