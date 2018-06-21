<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	

<!-- content -->
 <section class="container">
	<div class="generic-container">
	<div class="panel panel-default">
		  <!-- Default panel contents -->
	  	<div class="panel-heading" align="center"><span class="lead">Create Group </span></div>
	  	<div align="center" style="padding-top: 10px;padding-bottom: 10px;">${message}</div> 
	  	<div class="tablecontainer" style="padding-left: 155px;">
			 <form:form method="post" action="${contextPath}/group/create" modelAttribute="group">
				<table>
				    <tr>
				        <th>Group Name : </th>
				        <td><form:input path="name" cssStyle="width:400px;" /></td>
				    </tr>
				    <tr>
				    	<td>&nbsp;</td>
				    </tr>
				    <tr>
				        <th>Description : </th>
				        <td><form:textarea path="description" rows="4" cols="72" /></td>
				    </tr>
				    <tr>
				        <td colspan="2" align="center" style="padding-top: 10px;">
				            <input type="submit" value="Create" />
				            <input type="reset" value="Reset" />
				        </td>
				    </tr>
				</table>  
				<br />
			</form:form> 
	    </div>
	    
	</div>
	
  	</div>
</section>
