$(document).ready(function() {
    $(".content").hide();

    $("#showEditProfile").bind("click", function() {
        if($("#profile").is(":hidden")) {
            $("#profile").show("slow");
            $("#showEditProfile").html("Hide");
        } else {
            $("#profile").hide("slow");
            $("#showEditProfile").html("Edit profile");
        }
    })

    $("#editProfile").bind("click", function editProfile() {

        var baseUrl = "http://localhost:8080/adminRest/getPerson/";
        var name = $("#textArea").val();
        $.ajax({
            type: "GET",
            url: baseUrl + name,
            success: function(response) {
                $("#profileEdit").append("<tr><td>First name <textarea id='newPersonFirstName'>"  + response.firstName + "</textarea>" +
                "<input id='personId' type='hidden' value='" + response.personId +  "'></input></td></tr>");
                $("#profileEdit").append("<tr><td>Middle name <textarea id='newPersonMiddleName'>" + response.middleName + "</textarea></td></tr>");
                $("#profileEdit").append("<tr><td>Last name <textarea id='newPersonLastName'>" + response.lastName + "</textarea></td></tr>");
                $("#profileEdit").append("<tr><td>Login <textarea id='newPersonLogin'>" + response.login + "</textarea></td></tr>");
                $("#profileEdit").append("<tr><td>Password <textarea id='newPersonPassword'> password  </textarea></td></tr>");
                $("#profileEdit").append("<tr><td>Birth date <textarea id='oldPersonBirthDate'>" + new Date(response.birthDate) +
                "</textarea><br/><input id='newPersonBirthDate' class='form-field' type='date' value='newPersonBirthDate'>Birth date</input></td></tr>");
                $("#profileEdit").append("<tr><td>Status <textarea id='newPersonStatus'>" + response.status + "</textarea></td></tr>");
                $("#profileEdit").append("<tr><td><button class='button' id='updateProfile'><span>Update profile</span></button></td></tr>");
                $("#updateProfile").bind("click", function() {

                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    console.log($("#newPersonBirthDate").val());
                    var updatePerson = {
                        personId: $("#personId").val(),
                        firstName: $("#newPersonFirstName").val(),
                        middleName: $("#newPersonMiddleName").val(),
                        lastName: $("#newPersonLastName").val(),
                        login: $("#newPersonLogin").val(),
                        password: $("#newPersonPassword").val(),
                        birthDate: $("#newPersonBirthDate").val(),
                        status: $("#newPersonStatus").val()
                    }
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/adminRest/updatePerson",
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        data: JSON.stringify(updatePerson),
                        dataType: "json",
                        contentType: 'application/json',
                        success: function(response) {
                            $("#profileEdit").hide("slow");
                            $("#profileEdit").empty();
                            $("#h1top").html(response);
                            location.reload();
                        }
                    })
                })
            }


        })
    })

    $("#showDivForm").bind("click", function() {
        if($("#divForm").is(":hidden")) {
            $("#divForm").show("slow");
            $("#showDivForm").html("Hide");
        } else {
            $("#divForm").hide("slow");
            $("#showDivForm").html("Create person");
        }
    })

    $("#submitPerson").bind("click", function() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var person = {
            "firstName": $("#firstName").val(),
            "middleName": $("#middleName").val(),
            "lastName": $("#lastName").val(),
            "login": $("#login").val(),
            "password": $("#password").val(),
            "birthDate": $("#birthDate").val()
        }
        var role = {
            "login": $("#login").val(),
            "role": $("#role").val()
        }
        var postData = {
            "person": person,
            "role": role
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/adminRest/createPerson",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(postData),
            dataType: "json",
            contentType: 'application/json',
            success: function(response) {
                $("#h1top").empty();
                $("#h1top").html(response);
                $("#divForm").hide("slow");
                $("#showDivForm").html("Create person");
            },
        })
    })

    $("#showPersons").bind("click", function() {
            if($("#persons").is(":hidden")) {
                $("#persons").show("slow");
                $("#showPersons").html("Hide");
            } else {
                $("#persons").hide("slow");
                $("#showPersons").html("Show persons");
            }
    })

    $("#listOfPersons").bind("click", function() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/adminRest/listOfPersons",
            success: function(response) {
                var jsonObj = response;
                $("#tablePersons").append("<tr><th>Name</th><th>Login</th></tr>")
                for(var i = 0; i < jsonObj.length; i++) {
                    $("#tablePersons").append("<tr><td>" + jsonObj[i].firstName + "</td><td>" + jsonObj[i].login + "</td></tr>");
                    }
            }
        })
    })

})