<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>

<!DOCTYPE html>
<html lang="en">
<head>
<title><tiles:getAsString name="title"/></title>
<!-- stylesheets -->
<c:forEach var="css" items="${stylesheets}">
    <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>
<!-- end stylesheets -->


</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<body id="page1">
	<div class="bg">
	
	<!-- header -->
	<tiles:insertAttribute name="menu" />
		
	<!-- content -->
	<tiles:insertAttribute name="body" />
		
	</div>
	
	<!-- footer -->
	<tiles:insertAttribute name="footer" />
	
	<!-- scripts -->
    <c:forEach var="script" items="${javascripts}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>
    <!-- end scripts -->
    <script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
</body>
</html>

