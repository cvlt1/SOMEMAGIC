<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<body>

<div class="row">
<h1>test content outer</h1>
	<c:forEach var="contentItem" items="${contentItems}" varStatus="loopCounter">
		
			<div class="col s12">${contentItem.html}</div>
		
	</c:forEach>
</div>


</body>