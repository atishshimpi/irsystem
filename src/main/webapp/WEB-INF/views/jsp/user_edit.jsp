<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- content -->
<div class="generic-container" style="min-height: 550px;" align="center">
	<div class="panel panel-default">
		
		<form:form method="post" action="${contextPath}/user/edit" modelAttribute="user">
		<table>
			<%-- <tr>
				<th>id : </th>
				<td><form:input path="id" disabled="disabled" /></td>
			</tr> --%>
			<tr>
				<th>First Name : </th>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<th>Last Name : </th>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<th>Email : </th>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<th>Phone : </th>
				<td><form:input path="phone" /></td>
			</tr>
			<%-- <tr>
				<th>Role : </th>
				<td><form:input path="role" /></td>
			</tr> --%>
			<tr>
				<td colspan="2" align="center"  style="padding-top: 10px;">
					<input type="submit" value="Edit" />
					<input type="reset" value="Reset" />
				</td>
			</tr>
		</table>  
		<br />
		</form:form>
	</div>
</div>