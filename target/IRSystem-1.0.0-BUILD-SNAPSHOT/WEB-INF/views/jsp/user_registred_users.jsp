<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
   <script src="<c:url value="/resources/js/custom/users-jqgrid.js" />" type="text/javascript"></script>
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div class="generic-container" style="min-height: 550px; ">
	<div class="panel panel-default">
		
		<div class="panel-heading" align="center" style="padding-bottom: 20px;"><span class="lead"><h1>Registered Users</h1></span></div>
		<div class="uploadcontainer">
			<div id="jqgrid" align="center">
				<table id="grid"></table>
				<div id="pager"></div>
			</div>
			
			<div id="dialog" title="Feature not supported" style="display:none">
				<p>That feature is not supported.</p>
			</div>
			
			<div id="dialogSelectRow" title="Warning" style="display:none">
				<p>Please select row</p>
			</div>
			<input type="hidden" id="gridid" name="gridid" />
			</div>
	</div>
</div>

	