<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="imagesPath" value="${contextPath}/resources/images"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html>
	<head>
		<title>Checkout</title>
	</head>
	<body>
		<div id="bd" class="yui-t6">
			<div class="page-title">
				<h1 style="padding-right:20px;float:left">Checkout</h1>
				<div style="padding-top:3px"><a href="${flowExecutionUrl}&_eventId=cancelCheckout">[cancel checkout]</a></div>
				<div style="clear:both"></div>
			</div>
			<div id="yui-main">
				<div class="yui-b">
					<h2>Your Cart - ${shoppingCart.itemCount} items</h2>
					<jsp:include page="carttable.jsp"/>
					
					<h2>Recommendations</h2>
					<p>Based on your selections, we recommend the following:</p>
					<table>
						<c:forEach var="product" items="${recommendations}">
							<tr>
								<td><img class="product-thumb" src="${imagesPath}/${product.imageUrl}" alt="${product.description}" /></td>
								<td>${product.description}</td>
								<td class="numeric">${product.priceInDollars}</td>
								<td><a href="${flowExecutionUrl}&_eventId=addToCart&productId=${product.id}">[add to cart]</a></td>
							</tr>
						</c:forEach>
					</table>
				</div> <%-- end yui-b --%>
			</div> <%-- end yui-main --%>
			<div class="yui-b">
				<h2>New or Returning Customer?</h2>
				
				<p>New customers, please
				<a href="${flowExecutionUrl}&_eventId=register">register</a>.</p>
				
				<p>Returning customers, please log in:</p>
				<jsp:include page="account/loginbox.jsp"/>
				
			</div> <%-- end yui-b --%>
		</div> <%-- end bd --%>
	</body>
</html>
