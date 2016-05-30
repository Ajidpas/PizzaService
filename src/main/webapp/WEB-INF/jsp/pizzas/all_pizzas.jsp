<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="pizza_number_map" class="java.util.HashMap" />
<%-- <jsp:useBean id="listBean" class="com.example.ListBean" scope="request" />
<jsp:setProperty name="listBean" property="child" value="foo" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List of pizzas:</h1>
	
	<table cellspacing="1" cellpadding="4" border="3">
        <tr>
            <th>
                <h3>Pizza id</h3>
            </th>
            <th>
                <h3>Pizza name</h3>
            </th>
            <th>
                <h3>Pizza type</h3>
            </th>
            <th>
                <h3>Pizza price</h3>
            </th>
            <th>
                <h3>Pizza number</h3>
            </th>
        </tr>
        
		<c:forEach var="pizza" items="${pizzas}">
	        <tr>
	            <td>
	                <c:out value="${pizza.id}"/><p>
	            </td>
	            <td>
	                <a href="/PizzaService/app/pizzas/${pizza.id}">
	                	<c:out value="${pizza.name}"/><p>
	                </a>
	            </td>
	            <td>
	                <c:out value="${pizza.type}"/><p>
	            </td>
	            <td>
	                <c:out value="${pizza.price}"/><p>
	            </td>
	            <td>
	            	<form action="/PizzaService/app/pizzas/delete" method="POST" >
	            		<input type="submit" value="Delete pizza">
	            		<input type="hidden" name="pizza_id" value="${pizza.id}" />
            		</form>
	            	<%-- <select name="pizza_number" >
						<c:forEach begin="0" end="10" var="number" >
							<option value="${number}" >${number}</option>
						</c:forEach>
					</select> --%>
					<%-- <jsp:setProperty name="pizza_number_map" property="pizza_number" value="${pizza.id}"/> --%>
					<%-- <c:set target="pizza_number_map" property="pizza_number" value="${pizza.id}"/> --%>
	            </td>
	        </tr>
	    </c:forEach>
    </table>
    
    
</body>
</html>