<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Pages</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="path">path</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="status">status</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="title">title</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="template" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${page.id}" /></td>
				<td><c:out value="${page.path}" /></td>
				<td><c:out value="${page.status}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${page.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${page.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesTemplate}/${page.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesTemplate}/${page.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesTemplate}/${page.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesTemplate}/${page.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPage}/add"><i class="material-icons">add</i></a>