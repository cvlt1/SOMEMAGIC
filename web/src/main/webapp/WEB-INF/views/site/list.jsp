<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Sites</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesSite}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSite}" column="siteName">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSite}" column="basePath">basepath</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSite}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSite}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="site" items="${gridItems}" varStatus="loopCounter">
			<tr brandId="${site.id}" class="clickable-row">
				<td><c:out value="${site.id}" /></td>
				<td><c:out value="${site.siteName}" /></td>
				<td><c:out value="${site.basePath}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${site.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${site.updated}" /></td>
				<td class="right"><a class="btn-floating" href="${pagesSite}/${site.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesSite}/${site.id}/edit"><i class="material-icons">edit</i></a> <a
					class="btn-floating red" href="${pagesSite}/${site.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesSite}/add"><i
	class="material-icons">add</i></a>