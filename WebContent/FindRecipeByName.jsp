<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find Recipes By Name</title>
</head>
<body>
	<form action="FindRecipeByName" method="post">
		<h1>Search for Recipes by Name</h1>
		<p>
			<label for="rcpName">RecipeName:&nbsp;</label>
		</p>
		<p>
			<input type="submit" value="Search">
			<br/>
		</p>
	</form>

		
</body>
</html>
