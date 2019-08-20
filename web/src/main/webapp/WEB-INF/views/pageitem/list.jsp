<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">pagesPageItem</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="pageId">page id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="position">position</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="contentItemId">contentItemId by</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPageItem}"
					column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="pageItem" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${pageItem.id}" /></td>
				<td><c:out value="${pageItem.pageId}" /></td>
				<td><c:out value="${pageItem.position}" /></td>
				<td><c:out value="${pageItem.contentItemId}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${pageItem.created}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPageItem}/${pageItem.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesPageItem}/${pageItem.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesPageItem}/${pageItem.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPageItem}/${pageItem.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPageItem}/add"><i class="material-icons">add</i></a>