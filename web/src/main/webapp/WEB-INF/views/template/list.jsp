<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Templates</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTemplate}" column="id">id</mytaglib:sort-link></th>
<<<<<<< HEAD
			<th><mytaglib:sort-link pageUrl="${pagesTemplate}" column="view_name">viewName</mytaglib:sort-link></th>
=======
			<th><mytaglib:sort-link pageUrl="${pagesTemplate}" column="view_name">view_name</mytaglib:sort-link></th>
>>>>>>> 1deb6ea0a9cf2a6d3f30634f265d57b64dffcd8c
			<th><mytaglib:sort-link pageUrl="${pagesTemplate}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTemplate}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="template" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${template.id}" /></td>
				<td><c:out value="${template.viewName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${template.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${template.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesTemplate}/${template.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesTemplate}/${template.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesTemplate}/${template.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesTemplate}/${template.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesTemplate}/add"><i class="material-icons">add</i></a>