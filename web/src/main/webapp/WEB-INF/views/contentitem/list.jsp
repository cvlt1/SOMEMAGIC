<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">Content</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="html">html</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="siteId">site</mytaglib:sort-link></th>
             <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="title">title</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesContentItem}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="contentItem" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${contentItem.id}" /></td>
                <td><c:out value="${contentItem.html}" /></td>
                <td><c:out value="${contentItem.siteId}" /></td>
                <td><c:out value="${contentItem.title}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contentItem.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contentItem.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesContentItem}/${contentItem.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesContentItem}/${contentItem.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red disabled" href="${pagesContentItem}/${contentItem.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesContentItem}/add"><i class="material-icons">add</i></a>