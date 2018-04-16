<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="<c:url value="/resources/css/css.css" />" rel="stylesheet">
<script src="/resources/js/jquery.js" type="text/javascript"></script>
<script src="/resources/js/employee.js" type="text/javascript"></script>
</head>
<body>
	<h1 id="h1top" class="deepShadow">Hello ${pageContext.request.userPrincipal.name}</h1>
	<br/>

	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>


	<button class="button" id="addCustomer"><span>Add customer</span></button>
	<form id="addCustomerForm" class="form-container">
	    <input id="firstName" class="form-field" type="text" value="firstName">First name</input><br/>
	    <input id="middleName" class="form-field" type="text" value="middleName">Middle name</input><br/>
	    <input id="lastName" class="form-field" type="text" value="lastName">Last name</input><br/>
	    <input id="login" class="form-field" type="text" value="login">Login</input><br/>
	    <input id="password" class="form-field" type="text" value="password">Password</input><br/>
	    <input id="birthDate" class="form-field" type="date" value="birthDate">Birth date</input><br/>
	    <input class="submit-button"  type="button" id="submitCustomer" value="submit"/>
	</form><br/>

	<button class="button" id="addBook"><span>Add book</span></button>
	<form id="addBookForm" class="form-container">
	    <input id="name" class="form-field" type="text" value="name">Name</input><br/>
	    <input id="author" class="form-field" type="text" value="author">Author</input><br/>
	    <input id="description" class="form-field" type="text" value="description">Description</input><br/>
	    <input class="submit-button" type="button" id="submitBook" value="submit"/>
	</form><br/>
	<button class="button" id="addSubscription"><span>Add subscription</span></button>
	<form id="addSubscriptionForm" class="form-container">
	    <input id="book" class="form-field" type="text" value="book">Book</input><br/>
	    <input id="person" class="form-field" type="text" value="person">Person</input><br/>
	    <input class="submit-button" type="button" id="submitSubscription" value="submit"/>
	</form><br/>
	<button class="button" id="subscription"><span>Subscriptions</span></button><textarea id="textAreaSubscription" placeholder="Enter login of customer to get him subscriptions"></textarea><br/>
    <button class="button" id="searchBook"><span>Search</span></button><textarea id="textArea" placeholder="Enter book name or author to find book"></textarea><br/>
    <button class="button" id="allBooks"><span>All books</span></button>
    <button class="button" id="hide"><span>Hide</span></button><br/>
    <table id="bookTable">
    </table>
    <table id="foundBooks">
    </table>

    <sec:authorize access="hasAnyAuthority('employee', 'admin')">
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