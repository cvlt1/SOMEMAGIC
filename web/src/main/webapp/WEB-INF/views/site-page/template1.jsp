<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<body>


	<c:forEach var="item" items="${contentItems}" varStatus="loopCounter">
		<div class="row">
			<div class="col s12">${item.html}</div>
		</div>
	</c:forEach>



</body>