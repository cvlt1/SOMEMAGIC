<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">Content</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesPageHistory}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPageHistory}" column="siteId">site</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPageHistory}" column="changedBy">changed by</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPageHistory}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPageHistory}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="pageHistory" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${pageHistory.id}" /></td>
                <td><c:out value="${pageHistory.siteId}" /></td>
                <td><c:out value="${pageHistory.changedBy}" /></td>
                <td><c:out value="${pageHistory.title}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${pageHistory.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${pageHistory.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesPageHistory}/${pageHistory.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesPageHistory}/${pageHistory.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red disabled" href="${pagesPageHistory}/${pageHistory.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesPageHistory}/add"><i class="material-icons">add</i></a>