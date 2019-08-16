<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit css</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCssItem}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="content" type="text" disabled="${readonly}" />
				<form:errors path="content" cssClass="red-text" />
				<label for="content">content</label>

			</div>
			<div class="input-field col s12">
				<form:select path="siteId" disabled="${readonly}">
					<form:options items="${sitesChoices}" />
				</form:select>
				<form:errors path="siteId" cssClass="red-text" />
				<label for="siteId">siteId</label>
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
				<a class="btn waves-effect waves-light right"
					href="${pagesCssItem}">to list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>