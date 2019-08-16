<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">Css</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCssItem}"
					column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCssItem}"
					column="content">content</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCssItem}"
					column="siteId">site</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCssItem}"
					column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCssItem}"
					column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="cssItem" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${cssItem.id}" /></td>
				<td><c:out value="${cssItem.content}" /></td>
				<td><c:out value="${cssItem.siteId}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cssItem.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cssItem.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesCssItem}/${cssItem.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesCssItem}/${cssItem.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesContentItem}/${cssItem.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesContentItem}/${cssItem.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesCssItem}/add"><i class="material-icons">add</i></a>