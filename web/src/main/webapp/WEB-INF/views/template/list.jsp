<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Brands</h4>
<table class="bordered highlight">
<tbody>
<tr>
<th>id</th>
<th>jsp_path</th>
<th>created</th>
<th>updated</th>
<th></th>
</tr>
<c:forEach var="template" items="${gridItems}" varStatus="loopCounter">
<tr>
<td><c:out value="${template.id}" /></td>
<td><c:out value="${template.jspPath}" /></td>
<td><fmt:formatDate pattern="yyyy-MM-dd" value="${template.created}" /></td>
<td><fmt:formatDate pattern="yyyy-MM-dd" value="${template.updated}" /></td>
<td class="right"><a class="btn-floating" href="${pagesTemplate}/${template.id}"><i class="material-icons">info</i></a> 
<a class="btn-floating green" href="${pagesTemplate}/${template.id}/add"><i class="material-icons">add</i></a>
<a class="btn-floating" href="${pagesTemplate}/${template.id}/edit"><i class="material-icons">edit</i></a> 
<a class="btn-floating red" href="${pagesTemplate}/${template.id}/delete"><i class="material-icons">delete</i></a></td>

</tr>
</c:forEach>
</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesTemplate}/add"><i class="material-icons">add</i></a>