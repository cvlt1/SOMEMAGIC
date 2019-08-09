<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>home page</div>
<sec:authorize access="!isAnonymous()">
Logged user id: <sec:authentication property="id" />
	<br />
Login name: <sec:authentication property="principal" />
</sec:authorize>
<sec:authorize access="isAnonymous()">
Logged user is anonymous
</sec:authorize>


<c:set var="currentLocale" value="${sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE']}"/> 

		<style>
			.highlighted-menu-${currentLocale!=null?currentLocale:'ru'} {
			text-decoration: underline;
		}
		</style>


<header>





	<nav class="brown lighten-4">
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a class="grey-text text-darken-3" href="${contextPath}/">home</a></li>
				<li><a class="grey-text text-darken-3" href="${pagesTemplate}">Templates</a></li>
				<li><a class="grey-text text-darken-3" href="${pagesPage}">Pages</a></li>
				<li><a class="grey-text text-darken-3" href="${pagesUacc}">User Account</a></li>
				<li><a class="grey-text text-darken-3" href="${pagesSite}">Site</a></li>
				<a class="right" class="grey-text text-darken-3" href="${contextPath}/execute_logout" title="logout"><i
					class="material-icons" class="grey-text text-darken-3">arrow_forward</i></a>
				<li><a class="highlighted-menu-ru" class="grey-text text-darken-3" href="?lang=ru">RU</a></li>
				<li><a class="highlighted-menu-en" class="grey-text text-darken-3" href="?lang=en">EN</a></li>

			</ul>
		</div>
	</nav>
</header>