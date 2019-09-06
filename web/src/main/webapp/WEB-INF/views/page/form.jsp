<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit page</h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesPage}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:select path="siteId" disabled="${readonly}">
					<form:options items="${sitesChoices}" />
				</form:select>
				<form:errors path="siteId" cssClass="red-text" />
				<label for="siteId">site</label>
			</div>

			<div class="input-field col s12">
				<form:select path="templateId" disabled="${readonly}">
					<form:options items="${templatesChoices}" />
				</form:select>
				<form:errors path="templateId" cssClass="red-text" />
				<label for="templateId">template</label>
			</div>

			<div class="input-field col s12">
				<form:select path="creatorId" disabled="${readonly}">
					<form:options items="${uAccChoices}" />
				</form:select>
				<form:errors path="creatorId" cssClass="red-text" />
				<label for="creatorId">creator</label>
			</div>

			<div class="input-field col s12">
				<form:input path="path" type="text" disabled="${readonly}" />
				<form:errors path="path" cssClass="red-text" />
				<label for="path">path</label>
			</div>

			<div class="input-field col s12">
				<form:input path="pageStatus" type="text" disabled="${readonly}" />
				<form:errors path="pageStatus" cssClass="red-text" />
				<label for="pageStatus">status</label>
			</div>

			<div class="input-field col s12">
				<form:input path="pageTitle" type="text" disabled="${readonly}" />
				<form:errors path="pageTitle" cssClass="red-text" />
				<label for="pageTitle">title</label>
			</div>

		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesPage}">to
					list<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}'); // execute function defined somewhere above
	</script>

</c:if>