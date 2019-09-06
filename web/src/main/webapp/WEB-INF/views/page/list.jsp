<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Pages</h4>
<table class="bordered highlight">
	<tbody>
		<tr class="highlight">
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="siteId">site</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="templateId">template</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="path">path</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="status">status</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="creator">creator</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="title">title</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="pageItemId">pageItemId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPage}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="page" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${page.id}" /></td>
				<td><c:out value="${page.siteId}" /></td>
				<td><c:out value="${page.templateId}" /></td>
				<td><c:out value="${page.path}" /></td>
				<td><c:out value="${page.pageStatus}" /></td>
				<td><c:out value="${page.creatorId}" /></td>
				<td><c:out value="${page.pageTitle}" /></td>
				<td><c:out value="${page.pageItemId}" /></td>
				<td class="right"><a class="btn-floating green" href="${pagesPageItem}/add"><i class="material-icons">add</i></a> </td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${page.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${page.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPage}/${page.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesPage}/${page.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesPage}/${page.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPage}/${page.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPage}/add"><i class="material-icons">add</i></a>