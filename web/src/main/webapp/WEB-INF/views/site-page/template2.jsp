<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<body>
	<div class="row">
		<c:forEach var="contentItem" items="${contentItems}" varStatus="loopCounter">
			<div class="col s6">${item.html}</div>
		</c:forEach>
	</div>
</body>