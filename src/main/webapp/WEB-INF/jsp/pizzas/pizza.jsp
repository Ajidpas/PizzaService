<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Pizza: id = ${pizza.id}</h1>
	
	<h2>${error}</h2>

	<h2>Pizza name = ${pizza.name}</h2>
	<h2>Pizza price = ${pizza.price}</h2>
	<h2>Pizza type = ${pizza.type}</h2>
	<form action="/PizzaService/app/pizzas/edit" method="get" >
		<input type="submit" value="Edit pizza">
		<input type="hidden" name="pizza_id" value="${pizza.id}">
	</form>
</body>
</html>