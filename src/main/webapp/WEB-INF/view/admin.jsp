<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<link href="<c:url value="/resources/css/css.css" />" rel="stylesheet">
<script src="/resources/js/jquery.js" type="text/javascript"></script>
<script src="/resources/js/admin.js" type="text/javascript"></script>
</head>
<body>
	<h1 id="h1top" class="deepShadow">Hello ${pageContext.request.userPrincipal.name}</h1>
    <br/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <button class="button" id="showEditProfile"><span>Edit profile</span></button><br/>
    <div id="profile" class="content">
        <button class="button" id="editProfile"><span>Edit profile</span></button><br/>
        <textarea id="textArea" placeholder="Enter the login of profile"></textarea><br/>
        <table id="profileEdit">
        </table>
    </div>

    <button class="button" id="showDivForm"><span>Create person</span></button><br/>
    <div id="divForm" class="content">
        <form id="addPersonForm" class="form-container">
            <input id="firstName" class="form-field" type="text" value="firstName">First name</input><br/>
        	<input id="middleName" class="form-field" type="text" value="middleName">Middle name</input><br/>
        	<input id="lastName" class="form-field" type="text" value="lastName">Last name</input><br/>
        	<input id="login" class="form-field" type="text" value="login">Login</input><br/>
        	<input id="password" class="form-field" type="text" value="password">Password</input><br/>
        	<input id="birthDate" class="form-field" type="date" value="birthDate">Birth date</input><br/>
        	<input id="role" class="form-field" type="text" value="role">Role - admin or customer or employee</input><br/>
        	<input class="submit-button"  type="button" id="submitPerson" value="submit"/>
      	</form><br/>
    </div>

    <button class="button" id="showPersons"><span>Show persons</span></button><br/>
    <div id="persons" class="content">
        <button class="button" id="listOfPersons"><span>List of persons</span></button><br/>
        <table id="tablePersons">
        </table>
    </div>






	<sec:authorize access="hasAuthority('admin')">
	    <c:url value="/logout" var="logoutUrl" />
        	<form action="${logoutUrl}" method="post" id="logoutForm">
        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        	</form>
        	<script>
        		function formSubmit() {
        			document.getElementById("logoutForm").submit();
        		}
        	</script>

        	<c:if test="${pageContext.request.userPrincipal.name != null}">

            	    <h1 class="deepShadow">
                            <div id="book">
                                <a href="javascript:formSubmit()"> Logout</a>
                            </div>
            			</h1>
        	</c:if>
    </sec:authorize>

</body>
</html>