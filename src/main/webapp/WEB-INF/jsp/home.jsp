<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="imagesPath" value="${contextPath}/resources/images" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Products for Geeks</title>
</head>
<body>
	<div id="bd" class="yui-t6">
		<h1>Welcome to GeekWarez</h1>
		<p>The time on the server is ${serverTime}.</p>
		<div id="yui-main">
			<div class="yui-b">
				<h2>Your Cart</h2>
				Your cart currently contains ${shoppingCart.itemCount} items. <a
					href="${contextPath}/checkout.do">Checkout &raquo;</a>

				<h2>Our Products</h2>
				<table>
					<c:forEach var="product" items="${products}">
						<tr>
							<td><img class="product-thumb"
								src="${imagesPath}/${product.imageUrl}"
								alt="${product.description}" /></td>
							<td>${product.description}</td>
							<td class="numeric">${product.priceInDollars}</td>
							<td><a
								href="${contextPath}/addToCart.do?productId=${product.id}">[add
									to cart]</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="yui-b">
			<h2>Satisfaction Guaranteed</h2>
			<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
				Donec mattis metus sed est. Pellentesque facilisis facilisis dolor.
				Class aptent taciti sociosqu ad litora torquent per conubia nostra,
				per inceptos himenaeos. Praesent in libero at leo porta hendrerit.</p>

			<h2>Return Policy</h2>
			<p>Vestibulum ante ipsum primis in faucibus orci luctus et
				ultrices posuere cubilia Curae; Donec dignissim, risus ac convallis
				accumsan, felis leo feugiat purus, tempor blandit nunc ante vel dui.
				Nullam ut turpis id magna hendrerit tempor. Cum sociis natoque
				penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
		</div>
	</div>
</body>
</html>