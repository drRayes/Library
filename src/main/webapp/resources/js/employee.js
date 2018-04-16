$(document).ready(function(){
    $("#hide").hide();
    $("form").hide();
    $("#subscription").bind("click", function list() {
        $("bookTable").empty();
        var base_url = 'http://localhost:8080/book/list/';
        $.ajax({
            type: "GET",
            url: base_url + $("#textAreaSubscription").val(),
            success: function(response) {
                var jsonObj = response;
                $("#bookTable").html("");
                $("#hide").show("slow");
                $("#hide").bind("click", function hideBookTable() {
                    if ( $("#bookTable").is(":hidden")) {
                        $("#bookTable").show("slow");
                        $("#hide").html("Hide");
                      } else {
                        $("#bookTable").hide("slow");
                        $("#hide").html("Slide up");
                      }
                });
                $("#bookTable").append("<tr><th>Name</th><th>Author</th><th>Issue and return date</th></tr>");
                for(var i = 0; i < jsonObj.length; i++) {
                    $("#bookTable").append("<tr><td>" + jsonObj[i].book.name + "</td><td>" + jsonObj[i].book.author +
                    "</td><td>" + new Date(jsonObj[i].issueDate) + " -> " + new Date(jsonObj[i].returnDate) + "</td></tr>");
                }
            },
            dataType: "json"
        });
    });

    $("#allBooks").bind("click", function() {
        $("bookTable").empty();
        $("#hide").hide("slow");
            var base_url = 'http://localhost:8080/book/allBooks';
            $.ajax({
                type: "GET",
                url: base_url,
                success: function(response) {
                    var jsonObj = response;
                    $("#foundBooks").html("");
                    $("#foundBooks").append("<tr><th>Name</th><th>Author</th><th>Description</th><tr>");
                    for(var i = 0; i < jsonObj.length; i++) {
                        $("#foundBooks").append("<tr>><td>" + jsonObj[i].name + "</td><td>" + jsonObj[i].author +"</td><td>" +
                        jsonObj[i].description + "</td></tr>");
                    }
                },
                dataType: "json"
            });
    })

    $("#searchBook").bind("click", function searchBook() {
        $("table").empty();
        $("#hide").hide("slow");
        var base_url = 'http://localhost:8080/book/search/';
        $.ajax({
            type: "GET",
            url: base_url + $("#textArea").val(),
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
    $("#addCustomer").bind("click", function showForm() {
        if ($("#addCustomerForm").is(":hidden")) {
            $("#addCustomerForm").show("slow");
            $("#addCustomer").html("Hide");
        } else {
            $("#addCustomerForm").hide("slow");
            $("#addCustomer").html("Add customer");
        }
    })
    $("#submitCustomer").bind("click", function addCustomer() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var formData = {
            "firstName": $("#firstName").val(),
            "middleName": $("#middleName").val(),
            "lastName": $("#lastName").val(),
            "login": $("#login").val(),
            "password": $("#password").val(),
            "birthDate": $("#birthDate").val()
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/book/addCustomer",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: 'application/json',
            success: function(response) {
                $("#h1top").empty();
                $("#h1top").html("Person " + response.login + " was added!");
                $("#addCustomerForm").hide("slow");
                $("#addCustomer").html("Add customer");
            },

        })
    })

    $("#addBook").bind("click", function showForm() {
        if ($("#addBookForm").is(":hidden")) {
            $("#addBookForm").show("slow");
            $("#addBook").html("Hide");
        } else {
            $("#addBookForm").hide("slow");
            $("#addBook").html("Add book");
        }
    })
    $("#submitBook").bind("click", function addCustomer() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var formData = {
            "name": $("#name").val(),
            "author": $("#author").val(),
            "description": $("#description").val()
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/book/addBook",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: 'application/json',
            success: function(response) {
                $("#h1top").empty();
                $("#h1top").html("Book " + response.name + " was added!");
                $("#addBookForm").hide("slow");
                $("#addBook").html("Add book");
            },

        })
    })

    $("#addSubscription").bind("click", function showSubscription() {
        if ($("#addSubscriptionForm").is(":hidden")) {
            $("#addSubscriptionForm").show("slow");
            $("#addSubscription").html("Hide");
        } else {
            $("#addSubscriptionForm").hide("slow");
            $("#addSubscription").html("Add subscription");
        }
    })

    $("#submitSubscription").bind("click", function addCustomer() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var formData = {
            "book": {name: $("#book").val()},
            "person": {login: $("#person").val()},
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/book/addSubscription",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: 'application/json',
            success: function(response) {
                $("#h1top").empty();
                $("#h1top").html("Subscription with book name " + response.book.name + " and with login " + response.person.login + " was added!");
                $("#addSubscriptionForm").hide("slow");
                $("#addSubscription").html("Add subscription");
            },

        })
    })

});




