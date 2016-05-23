<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/PizzaService/pizzas/insert_pizza" method = "POST" >
		Pizza name: <input type="text" name="pizza_name"><br>
		Pizza type: <input type="text" name="pizza_type"><br>
		Pizza price: <input type="text" name="pizza_price"><br>
		<input type="submit" value="Add pizza">
	</form>
</body>
</html>