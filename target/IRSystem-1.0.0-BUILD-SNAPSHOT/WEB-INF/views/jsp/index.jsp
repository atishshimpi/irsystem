<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!---->
	<div>
			<div>
			
			<div class="wrap-in">
			<!---->
			<div class="slide-grid">
			  <div class="slider">
	    <div class="callbacks_container">
	      <ul class="rslides" id="slider">
			<li>
			<a href="single.html">
	         <div>
						<div>
							<img src="<%=request.getContextPath() %>/resources/images/slider-img1.jpg"></a>
							</div>
							<!-- <div class="col-md-7 banner-off" align="center">							
								<h2>FLAT 50% 0FF</h2>
								<label>FOR ALL PURCHASE <b>VALUE</b></label>
								<span class="on-get">GET NOW</span>
							</div> -->
							
							<div class="clearfix"> </div>
						</div>
						</a>
	        </li>
	        <li>
			<a href="single.html">
	         <div>
						<div class="col-md-5 banner-bag">
							<img src="<%=request.getContextPath() %>/resources/images/product-11_a-320x320.jpg"></a>
							</div>
							<div class="col-md-7 banner-off" align="center">							
								<h2>FLAT 50% 0FF</h2>
								<label>FOR ALL PURCHASE <b>VALUE</b></label>
								<span class="on-get">GET NOW</span>
							</div>
							
							<div class="clearfix"> </div>
						</div>
						</a>
	        </li>
	        <li>
			<a href="single.html">
	         <div class="banner-matter">
						<div class="col-md-5 banner-bag">
							<img src="<%=request.getContextPath() %>/resources/images/product31.png"></a>
							</div>
							<div class="col-md-7 banner-off" align="center">							
								<h2>FLAT 50% 0FF</h2>
								<label>FOR ALL PURCHASE <b>VALUE</b></label>
								<span class="on-get">GET NOW</span>
							</div>
							
							<div class="clearfix"> </div>
						</div>
						</a>
	        </li>
	       <li>
			<a href="single.html">
	         <div class="banner-matter">
						<div class="col-md-5 banner-bag">
							<img src="<%=request.getContextPath() %>/resources/images/product-14_a-320x320.jpg"></a>
							</div>
							<div class="col-md-7 banner-off" align="center">							
								<h2>FLAT 50% 0FF</h2>
								<label>FOR ALL PURCHASE <b>VALUE</b></label>
								<span class="on-get">GET NOW</span>
							</div>
							
							<div class="clearfix"> </div>
						</div>
						</a>
	        </li>
			<li>
			<a href="single.html">
	         <div class="banner-matter">
						<div class="col-md-5 banner-bag">
							<img src="<%=request.getContextPath() %>/resources/images/product-22_a-320x320.jpg"></a>
							
							</div>
							<div class="col-md-7 banner-off" align="center">							
								<h2>FLAT 50% 0FF</h2>
								<label>FOR ALL PURCHASE <b>VALUE</b></label>
								<span class="on-get">GET NOW</span>
							</div>
							
							<div class="clearfix"> </div>
						</div>
						</a>
	        </li>
	      </ul>
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
			<!----->
		
	            </div>
				
	          </div>
	         </div>
	   		      <!---->
				 
	   		     <div class="products">
	   		     	<h5 class="latest-product">PRODUCTS</h5>	
	   		     	  <!-- <a class="view-all" href="product.html">VIEW ALL<span> </span></a> --> 		     
	   		     	<div class="clearfix"> </div>
				 </div>
				 <div class="product-left"  style="padding-bottom:20px;">
	   		     	
	   		     	<c:forEach items="${userDocuments}" var="doc" varStatus="counter">
		   		     	<div class="col-md-4 chain-grid">
		   		     		<a href="${contextPath}/search/user_product_details?id=${doc.id}">
		   		     			<img src="${contextPath}/myImage/imageDisplay?id=${doc.id}" style="width: 241px;height: 244px;" />
		   		     		</a>
		   		     		
		   		     		<span class="star"> </span>
		   		     		<div class="grid-chain-bottom">
		   		     			<h6><a href="${contextPath}/search/user_product_details?id=${doc.id}"><span> </span>${doc.productName}</a></h6>
		   		     			<div class="star-price">
		   		     				<div class="dolor-grid"> 
			   		     				<span class="actual">${doc.productPrice}</span>
			   		     				<!-- <span class="reducedfrom">400$</span> -->
			   		     				  <span class="rating">
			   		     				  	<c:forEach var="i" begin="1" end="${doc.productRating}">
										   		<input type="radio" class="rating-input" id="rating-input-1-5" name="rating-input-1">
										  </c:forEach>
												
			   		     				  	<c:forEach var="i" begin="${doc.productRating+1}" end="5">
												<input type="radio" class="rating-input" id="rating-input-1-3" name="rating-input-1">
											</c:forEach>
			   		     				   </span>
		   		     				</div>
		   		     				<a class="now-get get-cart" href="#">ADD TO CART</a> 
		   		     				<div class="clearfix"> </div>
								</div>
		   		     		</div>
		   		     	</div>
		   		     	
		   		     	
		   		     	
	   		     	</c:forEach>
	   		     </div>
	   		    
	   		    
	   		     <div class="clearfix"> </div>
				 
	   		   </div>   
			   <div class="sub-cate">
				
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
					<%-- <div class=" top-nav rsidebar span_1_of_left">
						<h3 class="cate">Catalog Product</h3>
					</div>	
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
					<div class=" chain-grid menu-chain">
	   		     		<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/pi.jpg" alt=" " /></a>	   		     		
	   		     		<div class="grid-chain-bottom chain-watch">
		   		     		<span class="actual dolor-left-grid">450.00</span>
		   		     		<span class="reducedfrom">1,912.00</span>  
		   		     		<h6><a href="single.html">Zaveri Pearls Designer Austrian Diamond Necklace </a></h6>  		     			   		     										
	   		     		</div>
	   		     	</div> --%>
			</div>
	   		    <div class="clearfix"> </div>        	         
		</div>
	