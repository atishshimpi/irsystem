<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%-- <!-- content -->
 <section class="container">
	<div class="login">
	  <h1>User Registration</h1>
	  <form method="post" action="index.html">
		<p><input type="text" name="login" value="" placeholder="Username or Email"></p>
		<p><input type="password" name="password" value="" placeholder="Password"></p>
		<p class="submit"><input type="submit" name="commit" value="Login">&nbsp;&nbsp; <input type="submit" name="commit" value="Singup"></p>
		
	  </form>
	   ${message}
		 <form:form method="post" action="${contextPath}/user/create" modelAttribute="user">
			<table>
			    <tr>
			        <th>First Name: </th>
			        <td><form:input path="firstName" /></td>
			    </tr>
			    <tr>
			        <th>Last Name: </th>
			        <td><form:input path="lastName" /></td>
			    </tr>
			    <tr>
			        <th>email : </th>
			        <td><form:input path="email" /></td>
			    </tr>
			     <tr>
			        <th>email : </th>
			        <td><form:input path="email" /></td>
			    </tr>
			    <tr>
			        <th>phone : </th>
			        <td><form:input path="phone" /></td>
			    </tr>
			    <tr>
			        <th>password : </th>
			        <td><form:input path="password" /></td>
			    </tr>
			     <tr>
			        <th>Confirm password : </th>
			        <td><form:input path="confirmPassword" /></td>
			    </tr>
			 
			    <tr>
			        <td colspan="2" align="center" style="padding-top: 10px;">
			            <input type="submit" value="Register" />
			            <input type="reset" value="Reset" />
			        </td>
			    </tr>
			</table>  
			<br />
		</form:form> 
	  
	</div>

	<div align="center"><br/>
<!-- 	  <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p> -->
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</section> --%>

<div class="container">
	<form:form method="post" action="${contextPath}/user/create" modelAttribute="user">
	  ${message}
		<div class="account_grid">
		   <div class=" login-right">
		  	<h3>REGISTERED CUSTOMERS</h3>
			<p>If you have an account with us, please log in.</p>
			<form>
			  <div>
				<span>First Name:<label>*</label></span>
				<form:input path="firstName" />
			  </div>
			  <div>
				<span>Last Name:<label>*</label></span>
				<form:input path="lastName" />
			  </div>
			  <div>
				<span>Email:<label>*</label></span>
				<form:input path="email" />
			  </div>
			  <div>
				<span>Phone: <label>*</label></span>
				<form:input path="phone" />
			  </div>
			  <div>
				<span>Password:<label>*</label></span>
				<form:password path="password" />
			  </div>
			  <br/>
			  <input class="acount-btn" type="submit" value="Register" />
			  <input class="acount-btn" type="reset" value="Reset" />
		    </form>
		   </div>	
		   <div class="clearfix"> </div>
		</div>
	</form:form>
			 
			 
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
		
					<div class=" top-nav rsidebar span_1_of_left">
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
	   		     	<%-- 	<a href="single.html"><img class="img-responsive chain" src="<%=request.getContextPath() %>/resources/images/pi.jpg" alt=" " /></a>	   		     		
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
