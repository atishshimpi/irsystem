<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<header>
	<div class="border-bot">
		<div class="main zerogrid">
			<h1><a href="index.html">IRSystem</a></h1>
			<nav>
				<ul class="menu">
					<li><a class="active" href="${contextPath}/home">HOME</a></li>
					<li><a href="${contextPath}/user/displayLogin">USER</a></li>
					<li><a href="${contextPath}/user/displayAuthorityLogin">ADMIN</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
		</div>
	</div>
</header>
