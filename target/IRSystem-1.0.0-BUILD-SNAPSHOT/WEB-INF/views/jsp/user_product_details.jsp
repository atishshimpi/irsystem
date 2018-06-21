<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!---->
	<div class="container">
			<div class="shoes-grid">
			<div class="wrap-in">
			<!---->
			<div>
			  <div class="slider">
				    <div class="callbacks_container">
				    
				  	</div>
					<!-- <script src="js/responsiveslides.min.js"></script> --> 
				    <script>
					    $(function () {
					      $("#slider").responsiveSlides({
					      	auto: true,
					      	speed: 500,
					        namespace: "callbacks",
					        pager: true,
					      });
					    });
				  	</script>
	            </div>
				
	          </div>
	         </div>
	   		      <!---->
				 <div class="product-left"  style="padding-bottom:20px;">
	   		     </div>
	   		     <div class="product-left"  style="padding-bottom:20px;">
	   		     	
<%-- 	   		     	<c:forEach items="${userDocuments}" var="doc" varStatus="counter">
 --%>		   		     	<div class="col-md-4 chain-grid" style="height: 600px; width: 800px;">
		   		     		<a href="${contextPath}/search/user_product_details?id=${doc.id}">
		   		   	  			<img src="${contextPath}/myImage/imageDisplay?id=${userDocuments.id}" style="width: 241px;height: 244px;" />
		   		     		</a>
		   		     		
		   		     		<span class="star"> </span>
		   		     		<div class="grid-chain-bottom">
		   		     			<h6>${userDocuments.productName}</h6>
		   		     			<div class="star-price">
		   		     				<div class="dolor-grid"> 
			   		     				<span class="actual">${userDocuments.productPrice}</span>
			   		     				<!-- <span class="reducedfrom">400$</span> -->
			   		     				  <span class="rating">
			   		     				  	<c:forEach var="i" begin="1" end="${userDocuments.productRating}">
										   		<input type="radio" class="rating-input" id="rating-input-1-5" name="rating-input-1">
										        <label for="rating-input-1-5" class="rating-star1" onclick="javascript:updateRating(${i},${doc.id});"> </label>
											</c:forEach>
												
			   		     				  	<c:forEach var="i" begin="${userDocuments.productRating+1}" end="5">
												<input type="radio" class="rating-input" id="rating-input-1-3" name="rating-input-1">
										        <label for="rating-input-1-3" class="rating-star" onclick="javascript:updateRating(${i},${doc.id});"> </label>
											</c:forEach>
			   		     				   </span>
		   		     				</div>
		   		     				<a class="now-get get-cart" href="#">ADD TO CART</a> 
		   		     				<div class="clearfix"> </div>
		   		     				<%-- <span >${userDocuments.productDescription}</span> --%>
		   		     				<table>
		   		     					<tr>
		   		     						<td><span>Product Type : ${userDocuments.productType}</span></td>
		   		     					</tr>
		   		     					<tr>
		   		     						<td><span>Dealer Location : ${userDocuments.dealerLocation}</span></td>
		   		     					</tr>
		   		     					<tr>
		   		     						<td><span>No of Visits : ${userDocuments.noOfVisit}</span></td>
		   		     					</tr>
		   		     					<tr>
		   		     						<td><span>Description : ${userDocuments.productDescription}</span></td>
		   		     					</tr>
		   		     				</table>
		   		     			</div>
		   		     		</div>
		   		     	</div>
		   		     	
<%-- 	   		     	</c:forEach>
 --%>	   		     	 <div class="clearfix"> </div>
	   		     </div>
	   		    
	   		     <div class="product-left">
	   		     	
	   		     	 <div class="clearfix"> </div>
	   		     </div>
	   		     <div class="clearfix"> </div>
				 
	   		   </div>   
			   <div class="sub-cate">
				<div class=" top-nav rsidebar span_1_of_left">
					<h3 class="cate">Search Product</h3>
					 <ul class="menu">
						<ul class="kid-menu">
							<li><a href="${contextPath}/search/displayQueryValue"><span> </span>Query Value</a></li>
							<li ><a href="${contextPath}/search/displayContentValue">Content Value</a></li>
							<li><a href="${contextPath}/search/displayQueryContentValue">Combination of QV and CV</a></li>
							<li><a href="${contextPath}/search/displayMappingAlgorithm">Mapping Algorithm</a></li>
						</ul>
					</ul>
					</div>
				<!--initiate accordion-->
		<script type="text/javascript">
			$(function() {
			    var menu_ul = $('.menu > li > ul'),
			           menu_a  = $('.menu > li > a');
			    menu_ul.hide();
			    menu_a.click(function(e) {
			        e.preventDefault();
			        if(!$(this).hasClass('active')) {
			            menu_a.removeClass('active');
			            menu_ul.filter(':visible').slideUp('normal');
			            $(this).addClass('active').next().stop(true,true).slideDown('normal');
			        } else {
			            $(this).removeClass('active');
			            $(this).next().stop(true,true).slideUp('normal');
			        }
			    });
			
			});
		</script>
				<div class=" chain-grid menu-chain">
  		     		<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/girl1.jpg" alt=" " /></a>	   		     		
  		     	</div>
			</div>
		</div>
