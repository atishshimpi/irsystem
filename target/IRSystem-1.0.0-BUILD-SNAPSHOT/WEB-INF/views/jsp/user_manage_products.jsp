<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<head>	
	<script>
	    function popup(userid, documentId) {
	        window.open("../csp/displayShareFile?userid="+userid+"&documentId="+documentId, 'window', 'width=1200,height=500');
	    }
	    
		function popupGroup(userid, documentId) {
	        window.open("../sharefile/displayGroupShareFile?userid="+userid+"&documentId="+documentId, 'window', 'width=1200,height=500');
	    }
	    
	</script> 
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<br/>
<div class="generic-container">
	<div class="panel panel-default">
		<div class="panel-heading"><span class="lead">Upload New Document</span></div>
		<div class="uploadcontainer">
			<form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
		
				<%-- <div class="row">
					<div class="form-group col-md-12" style="margin-left:132px;">
						<label class="col-md-3 control-lable" for="file">Upload a document</label>
						<div class="col-md-7">
							<form:input type="file" path="file" id="file" class="form-control input-sm"/>
							<div class="has-error">
								<form:errors path="file" class="help-inline"/>
							</div>
						</div>
					</div>
				</div> --%>
				<div  style="margin-left:350px;padding-bottom: 10px;"><font color="green">${message}</font></div>
				<div class="row" style="margin-left:180px;">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Upload a document</label>
						<div class="col-md-7">
							<form:input type="file" path="file" id="file" class="form-control input-sm"/>
							<div class="has-error">
								<form:errors path="file" class="help-inline"/>
							</div>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Product Name</label>
						<div class="col-md-7">
							<form:input type="text" path="productName" id="productName" class="form-control input-sm"/>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Description</label>
						<div class="col-md-7">
							<form:textarea type="text" cols="10" rows="4" path="productDescription" id="productDescription" class="form-control input-sm"/>
						</div>
						
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Product Type</label>
						<div class="col-md-7">
							<%-- <form:input type="text" path="productType" id="productType" class="form-control input-sm"/> --%>
							<form:select path="productType" cssClass="form-control input-sm">
								<form:option value="NONE" label="--- Select ---"/>
   								<form:options items="${typeList}" />
							</form:select>
						</div>
						
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Product Price</label>
						<div class="col-md-7">
							<form:input type="text" path="productPrice" id="productPrice" class="form-control input-sm"/>
						</div>
						
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Dealer Location</label>
						<div class="col-md-7">
							<%-- <form:input type="text" path="dealerLocation" id="dealerLocation" class="form-control input-sm"/> --%>
							<form:select path="dealerLocation" cssClass="form-control input-sm">
								<form:option value="NONE" label="--- Select ---"/>
   								<form:options items="${locationList}" />
							</form:select>
						</div>
						
					</div>
				</div>
		
				<div class="row"  style="margin-left:350px;padding-bottom: 20px;">
					<div class="form-actions floatRight">
						<input type="submit" value="Upload" class="btn btn-primary btn-sm">
					</div>
				</div>

			</form:form>
			</div>
			
	</div>
	<div class="panel panel-default">
		  <!-- Default panel contents -->
	  	<div class="panel-heading"><span class="lead">List of uploaded files </span></div>
	  	<div class="tablecontainer">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>No.</th>
				        <th width="250">Name</th>
				        <th width="250">Type</th>
				        <th width="250">Price</th>
				         <th width="250">Location</th>
				         <th width="250">No Of Visits</th>
				         <th width="250">Rating</th>
				        <th width="100">Delete</th>
				   </tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${documents}" var="doc" varStatus="counter">
					<tr>
						<td>${counter.index + 1}</td>
						<td>${doc.productName}</td>
						<td>${doc.productType}</td>
						<td>${doc.productPrice}</td>
						<td>${doc.dealerLocation}</td>
						<td>${doc.noOfVisit}</td>
						<td>${doc.productRating}</td>
						<%-- <td><a href="<c:url value='/file/download-document-${user.id}-${doc.id}' />" class="btn btn-success custom-width">Update</a></td> --%>
						<td><a href="<c:url value='/file/delete-document-${user.id}-${doc.id}' />" class="btn btn-danger custom-width">delete</a></td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
	    </div>
	  </div>
  	</div>
