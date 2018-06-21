<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<script src="<c:url value="/resources/js/custom/users-jqgrid_sharefile.js" />" type="text/javascript"></script>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

	
<div class="panel panel-default">
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
				<input type="hidden" id="userid" name="userid" value="${userid}" />
				<input type="hidden" id="documentId" name="documentId" value="${documentId}" />
				
				</div>
		</div>
   	</div>
</div>
		
   	
	
