<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script type="application/x-javascript"> 
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
	</script>
		
</head>
<body id="page1">
	<!--==============================header=================================-->
		<tiles:insertAttribute name="header" />

	<!--==============================content================================-->
		<div class="container">
		
			<tiles:insertAttribute name="body" />
		</div>

	<!--==============================footer=================================-->
		<tiles:insertAttribute name="footer" />

	<!-- scripts -->
    <c:forEach var="script" items="${javascripts}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>
    <!-- end scripts -->
    <script type="text/javascript">
		var jq = jQuery.noConflict();
	</script>
	
	<script type="text/javascript">Cufon.now();</script>
	<script type="text/javascript">
		jq(window).load(function() {
			jq('.slider')._TMS({
				duration:1000,
				easing:'easeOutQuint',
				preset:'diagonalFade',
				slideshow:7000,
				banners:false,
				pauseOnHover:true,
				pagination:true,
				pagNums:false
			});
		});
		
		function updateRating(rating, productId){
			var data = {}
			data["rating"] = rating;
			data["documentId"] = productId;
			
			jq.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${contextPath}/ajax/update-rating",
				data : JSON.stringify(data),
				dataType : 'json',
				async : true,
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					//alert("Product rating updated succesfully !")
					
					//if (reload_on_return) {
                		setTimeout(
		                  function() 
		                  {
		                     location.reload();
		                  }, 0001);    
		            //}
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			
		}
		
		
		function addVisit(productId){

			var data = {}
			data["documentId"] = productId;
			
			jq.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${contextPath}/ajax/update-visits",
				async : true,
				data : JSON.stringify(data),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			
		}
		
	</script>
</body>
</html>