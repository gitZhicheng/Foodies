<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recipe Create</title>
</head>
<body>
	<h1>Recipe Create</h1>
	<form action="RecipeCreate" method="post">
		<p>
			<label for="recipeName">RecipeName:&nbsp;</label>
			<input id="recipeName" name="recipeName" value="">
		</p>
		<p>
			<label for="desc">Description:&nbsp;</label>
			<input id="desc" name="desc" value="" type="text">
		</p>
		<p>
			<label for="ingredient">Ingredient:&nbsp;</label>
			<input id="ingredient" name="ingredient" value="" type="text">
		</p>
		<p>
			<label for="step">Steps:&nbsp;</label>
			<input id="step" name="step" value="" type="text">
		</p>
		<p>
			<label for="cookingTime">Cooking Time:&nbsp;</label>
			<input id="cookingTime" name="cookingTime" value="">
		</p>
		<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}" type="hidden" />
		<p>
			<input type="submit" value="Create">
		</p>
	</form>
		
</body>
</html>