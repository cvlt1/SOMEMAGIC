<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Accs</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="email">email</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="email">password</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="role">role</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUacc}" column="status">status</mytaglib:sort-link></th>
			
			<th></th>
		</tr>
		<c:forEach var="uAcc" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${uAcc.id}" /></td>
				<td><c:out value="${uAcc.name}" /></td>
				<td><c:out value="${uAcc.email}" /></td>
				<td><c:out value="${uAcc.password}" /></td>
				<td><c:out value="${uAcc.role}" /></td>
				<td><c:out value="${uAcc.status}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesUacc}/${uAcc.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating green"
					href="${pagesUacc}/${uAcc.id}/add"><i
						class="material-icons">add</i></a> <a class="btn-floating"
					href="${pagesUacc}/${uAcc.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesUacc}/${uAcc.id}/delete"><i
						class="material-icons">delete</i></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesUacc}/add"><i class="material-icons">add</i></a>