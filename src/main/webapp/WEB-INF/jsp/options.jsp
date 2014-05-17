<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html>
	<head>
		<title>Shipping and Payment Options</title>
	</head>
	<body>
		<div id="bd">
			<div class="page-title">
				<h1 style="padding-right:20px;float:left;position:relative;bottom:0">Complete Your Checkout</h1>
				<a href="${flowExecutionUrl}&_eventId=back">[back to cart]</a>
				<div style="clear:both"></div>
			</div>
			<form method="post" action="${flowExecutionUrl}">
				<div class="yui-g">
					<div class="yui-u first">
						<h2 style="margin-top:0">Shipping Information</h2>
						<div style="margin:20px 0">
							Choose:
							<select>
								<c:forEach var="option" items="${shippingOptions}">
									<option>${option}</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<p>Where would you like us to ship your order?</p>
							<table>
								<tr>
									<td>Address 1:</td>
									<td><input type="text" name="address1" /></td>
								</tr>
								<tr>
									<td>Address 2:</td>
									<td><input type="text" name="address2" /></td>
								</tr>
								<tr>
									<td>City:</td>
									<td><input type="text" name="city" /></td>
								</tr>
								<tr>
									<td>State:</td>
									<td><input type="text" name="state" /></td>
								</tr>
								<tr>
									<td>ZIP Code:</td>
									<td><input type="text" name="zipCode" /></td>
								</tr>
							</table>
						</div>
					</div> <%-- end yui-u --%>
					<div class="yui-u">
						<h2 style="margin-top:0">Payment Information</h2>
						<p>Please provide your payment information.</p>
						<table>
							<tr>
								<td>Credit Card Type:</td>
								<td>
									<select>
										<option>Visa</option>
										<option>MasterCard</option>
										<option>Discover</option>
										<option>American Express</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>Credit Card Number:</td>
								<td><input type="text" name="ccNumber" /></td>
							</tr>
							<tr>
								<td>Expiration Date:</td>
								<td><input type="text" name="city" /></td>
							</tr>
						</table>
						
					</div> <%-- end yui-u --%>
				</div> <%-- end yui-g --%>
			
				<%-- This triggers a submit event --%>
				<div style="text-align:center">
					<input type="submit" name="_eventId_submit" value="Submit" />
					<button style="margin-left:5px" onclick="window.location='${flowExecutionUrl}&_eventId=cancelCheckout'">Cancel Checkout</button>
				</div>
			</form>
		</div> <%-- end bd --%>
	</body>
</html>
