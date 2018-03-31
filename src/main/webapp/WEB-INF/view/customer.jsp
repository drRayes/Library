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
             <div id="book">
                 <button class="button" id="book" style="vertical-align:middle"><span>Subscriptions</span></button>
                 | <a href="javascript:formSubmit()"> Logout</a>
                               </div>
			</h1>
		</c:if>
	</sec:authorize>
	<button class="button" id="hide" style="vertical-align:middle"><span>Hide</span></button>
    <table id="bookTable">
    </table>
    <textarea id="textArea" placeholder="Enter book name or author to find book"></textarea><button class="button" id="searchBook" style="vertical-align:middle"><span>Search</span></button>
    <table id="foundBooks">
    </table>
</body>
</html>