<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Pages</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>path</th>
			<th>title</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="page" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${page.id}" /></td>
				<td><c:out value="${page.path}" /></td>
				<td><c:out value="${page.title}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${page.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${page.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPage}/${page.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating green" href="${pagesPage}/${page.id}/add"><i	class="material-icons">add</i></a> 
					<a class="btn-floating"	href="${pagesPage}/${page.id}/edit"><i class="material-icons">edit</i></a>
					<a class="btn-floating red" href="${pagesPage}/${page.id}/delete"><i class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesPage}/add"><i
	class="material-icons">add</i></a>