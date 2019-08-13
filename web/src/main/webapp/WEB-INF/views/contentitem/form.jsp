<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit content</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesContentItem}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="html" type="text" disabled="${readonly}" />
				<form:errors path="html" cssClass="red-text" />
				<label for="name">html</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="siteId" disabled="${readonly}">
					<form:options items="${sitesChoices}" />
				</form:select>
				<form:errors path="siteId" cssClass="red-text" />
				<label for="siteId">siteId</label>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<form:input path="title" type="text" disabled="${readonly}" />
					<form:errors path="title" cssClass="red-text" />
					<label for="name">title</label>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesContentItem}">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>