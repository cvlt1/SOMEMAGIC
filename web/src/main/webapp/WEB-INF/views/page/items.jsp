<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<c:set var="currentPageUrl" value="${contextPath}/page/${page.id}/items" scope="page" />

<h4 class="header">Page ${page.id} available content items</h4>
<table class="bordered highlight">
	<tbody>
		<tr class="highlight">
			<th>id</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${applicableItems}">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td class="right"><a class="btn-floating red"
					href="${pagesPage}/${page.id}/items/${item.id}/add2page"><i
						class="material-icons">add</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>







<h4 class="header">Page ${page.id} selected page items</h4>
<table class="bordered highlight">
	<tbody>
		<tr class="highlight">
			<th>id</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${selectedItems}">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td class="right"><a class="btn-floating red"
					href="${pagesPage}/${page.id}/items/${item.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

