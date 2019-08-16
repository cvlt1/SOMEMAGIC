<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit content</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesPageHistory}"
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
				<form:input path="comment" type="text" disabled="${readonly}" />
				<form:errors path="comment" cssClass="red-text" />
				<label for="comment">comment</label>
			</div>
			<div class="input-field col s12">
				<form:select path="changedBy" disabled="${readonly}">
					<form:options items="${uAccChoices}" />
				</form:select>
				<form:errors path="changedBy" cssClass="red-text" />
				<label for="changedBy">changed by</label>
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
					href="${pagesPageHistory}">to list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>