<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recipe Update</title>
</head>
<body>
	<h1>Recipe Update</h1>
	<form action="RecipeUpdate" method="post">
		
		<p>
			<label for="recipeName">RecipeName:&nbsp;</label>
			<input id="recipeName" name="recipeName" value="<c:out value="${recipe.getPostName()}"/>">
		</p>
		<p>
			<label for="desc">Description:&nbsp;</label>
			<input id="desc" name="desc" value="<c:out value="${recipe.getDescription()}"/>" type="text">
		</p>
		<p>
			<label for="ingredient">Ingredient:&nbsp;</label>
			<input id="ingredient" name="ingredient" value="<c:out value="${recipe.getIngredientid()}"/>" type="text">
		</p>
		<p>
			<label for="step">Steps:&nbsp;</label>
			<input id="step" name="step" value="<c:out value="${recipe.getStep()}"/>" type="text">
		</p>
		<p>
			<label for="cookingTime">Cooking Time:&nbsp;</label>
			<input id="cookingTime" name="cookingTime" value="<c:out value="${recipe.getCookingTime()}"/>">
		</p>
		<input id="recipeId" name="recipeId" value="<c:out value="${recipe.getRecipeId()}"/>" type="hidden" />
		<input id="userId" name="userId" value="<c:out value="${recipe.getExperienced().getUserId()}"/>" type="hidden" />
		<input id="image" name="image" value="<c:out value="${recipe.getImage()}"/>" type="hidden" />
		<p>
			<input type="submit" value="Update">
		</p>
	</form>
		
</body>
</html>