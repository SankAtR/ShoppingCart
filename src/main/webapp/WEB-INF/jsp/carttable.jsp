<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${empty shoppingCart.items}">
		<p>Your cart is empty.</p>
	</c:when>
	<c:otherwise>
		<table>
			<tr>
				<th>Item</th>
				<th class="numeric">Quantity</th>
				<th class="numeric">Unit Price</th>
				<th class="numeric">Total</th>
			</tr>
			<c:forEach var="item" items="${shoppingCart.items}">
				<tr>
					<td>${item.product.description}</td>
					<td class="numeric">${item.quantity}</td>
					<td class="numeric">${item.unitPriceInDollars}</td>
					<td class="numeric">${item.totalPriceInDollars}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>TOTAL:</td>
				<td></td>
				<td></td>
				<td class="numeric">${shoppingCart.totalPriceInDollars}</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
