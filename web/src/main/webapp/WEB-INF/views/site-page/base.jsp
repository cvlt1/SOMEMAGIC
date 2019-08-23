<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="request" />


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${siteName}</title>

</head>

<tiles:insertAttribute name="body" />
</html>