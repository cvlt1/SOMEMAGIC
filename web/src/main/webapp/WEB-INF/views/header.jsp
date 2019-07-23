<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div>home page</div>
<sec:authorize access="!isAnonymous()">
Logged user id: <sec:authentication property="id" />
<br />
Login name: <sec:authentication property="principal" />
</sec:authorize>
<sec:authorize access="isAnonymous()">
Logged user is anonymous
</sec:authorize>
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}/">home</a></li>
				<li><a href="${pagesTemplate}">Templates</a></li>
                <li><a href="${pagesPage}">Pages</a></li>
                <li><a href="${pagesUacc}">User Account</a></li>
                <a class="right" href="${contextPath}/execute_logout" title="logout"><i
class="material-icons">arrow_forward</i></a>

			</ul>
		</div>
	</nav>
</header>