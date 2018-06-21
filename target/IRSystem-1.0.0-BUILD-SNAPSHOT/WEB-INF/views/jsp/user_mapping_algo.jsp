<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!---->
	<div class="container">
			<div class="shoes-grid">
				<form:form method="post" action="${contextPath}/search/mapping_algorithm" modelAttribute="queryText">
				  ${message}
					<div>
					   <div class=" login-right">
						  <div>
							<span>Query Text<label>*</label></span>
							Pattern : (annotation:value) e.g. : location:pune 
							<form:textarea path="text" cols="125" rows="5" />
						  </div>
						  <br/>
						  <input type="submit" name="commit" value="Search">
					   </div>	
					   <div class="clearfix"> </div>
					</div>
				</form:form>
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
				 
	   		     <div class="products">
	   		     		<h5 class="latest-product">RECOMANDED PRODUCTS</h5>	
	   		     	  <!-- <a class="view-all" href="product.html">VIEW ALL<span> </span></a> --> 		     
	   		     	<div class="clearfix"> </div>
				 </div>
				 <div class="product-left"  style="padding-bottom:20px;">
	   		     	
	   		     </div>
	   		
	   		     <div class="product-left"  style="padding-bottom:20px;">
	   		     	
	   		     	<c:forEach items="${userDocuments}" var="doc" varStatus="counter">
		   		     	<div class="col-md-4 chain-grid">
		   		     		<a onclick="javascript:addVisit(${doc.id});"  href="${contextPath}/search/user_product_details?id=${doc.id}">
		   		     		<img src="${contextPath}/myImage/imageDisplay?id=${doc.id}" style="width: 241px;height: 244px;" />
		   		     		<%-- <img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/ch.jpg" alt=" " /> --%>
		   		     		
		   		     		</a>
		   		     		
		   		     		<span class="star"> </span>
		   		     		<div class="grid-chain-bottom">
		   		     			<h6><a href="${contextPath}/search/user_product_details?id=${doc.id}"><span> </span>${doc.productName}</a></a></h6>
		   		     			<div class="star-price">
		   		     				<div class="dolor-grid"> 
			   		     				<span class="actual">${doc.productPrice}</span>
			   		     				<!-- <span class="reducedfrom">400$</span> -->
			   		     				  <span class="rating">
			   		     				  	<c:forEach var="i" begin="1" end="${doc.productRating}">
										   		<input type="radio" class="rating-input" id="rating-input-1-5" name="rating-input-1">
										        <label for="rating-input-1-5" class="rating-star1" onclick="javascript:updateRating(${i},${doc.id});"> </label>
											</c:forEach>
												
			   		     				  	<c:forEach var="i" begin="${doc.productRating+1}" end="5">
												<input type="radio" class="rating-input" id="rating-input-1-3" name="rating-input-1">
										        <label for="rating-input-1-3" class="rating-star" onclick="javascript:updateRating(${i},${doc.id});"> </label>
											</c:forEach>
			   		     				   </span>
		   		     				</div>
		   		     				<a class="now-get get-cart" href="#">ADD TO CART</a> 
		   		     				<div class="clearfix"> </div>
								</div>
		   		     		</div>
		   		     	</div>
		   		     	
	   		     	</c:forEach>
	   		     	 <div class="clearfix"> </div>
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
					<!--<li class="item1"><a href="#">Query Value<img class="arrow-img" src="images/arrow1.png" alt=""/> </a>
						<ul class="cute">
							<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
							<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
							<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
						</ul>
					</li>
					<li class="item2"><a href="#">Dignissim purus <img class="arrow-img " src="images/arrow1.png" alt=""/></a>
						<ul class="cute">
							<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
							<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
							<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
						</ul>
					</li>
					<li class="item3"><a href="#">Ultrices id du<img class="arrow-img img-arrow" src="images/arrow1.png" alt=""/> </a>
						<ul class="cute">
							<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
							<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
							<li class="subitem3"><a href="product.html">Automatic Fails</a></li>
						</ul>
					</li>
					<li class="item4"><a href="#">Cras iacaus rhone <img class="arrow-img img-left-arrow" src="images/arrow1.png" alt=""/></a>
						<ul class="cute">
							<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
							<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
							<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
						</ul>
					</li>
							<li>
						<ul class="kid-menu">
							<li><a href="product.html">Tempus pretium</a></li>
							<li ><a href="product.html">Dignissim neque</a></li>
							<li ><a href="product.html">Ornared id aliquet</a></li>
						</ul>
					</li> -->
					<ul class="kid-menu ">
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
	   		     		<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/samsung_syncmaster_941bw-210x210.jpg" alt=" " /></a>	   		     		
	   		     		<div class="grid-chain-bottom chain-watch">
		   		     		<span class="actual dolor-left-grid">1,615.00</span>
		   		     		<span class="reducedfrom">1,895.00</span>  
		   		     		<h6><a href="single.html">Titan Karishma Analog</a></h6>  		     			   		     										
	   		     		</div>
	   		     	</div>
					<div class=" chain-grid menu-chain">
	   		     		<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/product1.jpg" alt=" " /></a>	   		     		
	   		     		<div class="grid-chain-bottom chain-watch">
		   		     		<span class="actual dolor-left-grid">1,509.67</span>
		   		     		<span class="reducedfrom">2,499.00</span>  
		   		     		<h6><a href="single.html">Nike Jordan</a></h6>  		     			   		     										
	   		     		</div>
	   		     	</div>
					<%-- <div class=" chain-grid menu-chain">
	   		     		<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/pi.jpg" alt=" " /></a>	   		     		
	   		     		<div class="grid-chain-bottom chain-watch">
		   		     		<span class="actual dolor-left-grid">450.00</span>
		   		     		<span class="reducedfrom">1,912.00</span>  
		   		     		<h6><a href="single.html">Zaveri Pearls Designer Austrian Diamond Necklace </a></h6>  		     			   		     										
	   		     		</div>
	   		     	</div> --%>
	   		     	<!--  <a class="view-all all-product" href="product.html">VIEW ALL PRODUCTS<span> </span></a> --> 	
			</div>
	   		    <div class="clearfix"> </div>        	         
		</div>
