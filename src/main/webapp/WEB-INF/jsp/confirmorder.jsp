<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html>
	<head>
		<title>Confirm Order</title>
	</head>
	<body>
		<div id="bd">
			<h1>Confirm Order</h1>
			<p>Here is your order:</p>
			<jsp:include page="carttable.jsp"/>
			<p>
				<a href="${flowExecutionUrl}&_eventId=continue">Continue</a> |
				<a href="${flowExecutionUrl}&_eventId=cancelCheckout">Cancel</a>
			</p>
		</div>
	</body>
</html>
