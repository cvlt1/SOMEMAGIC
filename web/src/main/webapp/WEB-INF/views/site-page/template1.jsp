<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<body>
<h1>outer test content before row</h1>
<div class="row">
<h1>outer test content</h1>
	<c:forEach var="item" items="${contentItems}" varStatus="loopCounter">
		<h1>inner test content</h1>
		<div class="col s12">inner test content</div>
			<div class="col s12">${item.html}</div>
		
	</c:forEach>
</div>


</body>