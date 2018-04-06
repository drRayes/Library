<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="<c:url value="/resources/css/css.css" />" rel="stylesheet">
<script src="/resources/js/jquery.js" type="text/javascript"></script>
<script src="/resources/js/app.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="deepShadow">Hello ${pageContext.request.userPrincipal.name}</h1>
	<br/>
	<button class="button" id="book"><span>Subscriptions</span></button><br/>
    <button class="button" id="hide"><span>Hide</span></button><br/>
    <table id="bookTable">
    </table>
    <button class="button" id="searchBook"><span>Search</span></button><textarea id="textArea" placeholder="Enter book name or author to find book"></textarea><br/>
    <table id="foundBooks">
    </table>

	<sec:authorize access="hasAuthority('customer')">
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h1 class="deepShadow">
                <a href="javascript:formSubmit()"> Logout</a>
			</h1>
		</c:if>
	</sec:authorize>

</body>
</html>