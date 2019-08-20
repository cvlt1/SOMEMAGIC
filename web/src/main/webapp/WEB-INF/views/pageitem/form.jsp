<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit page item</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesPageItem}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:select path="pageId" disabled="${readonly}">
					<form:options items="${pagesChoices}" />
				</form:select>
				<form:errors path="pageId" cssClass="red-text" />
				<label for="pageId">page id</label>
			</div>

			<div class="input-field col s12">
				<form:input path="position" type="text" disabled="${readonly}" />
				<form:errors path="position" cssClass="red-text" />
				<label for="position">position</label>
			</div>
			<div class="input-field col s12">
				<form:select path="contentItemId" disabled="${readonly}">
					<form:options items="${contentItemChoices}" />
				</form:select>
				<form:errors path="contentItemId" cssClass="red-text" />
				<label for="contentItemId">contentItemId by</label>
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
					href="${pagesPageItem}">to list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>