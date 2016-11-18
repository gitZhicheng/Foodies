<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find Recipes</title>
</head>
<body>
	<form action="FindRecipes" method="post">
		<h1>Search for Recipes by UserId</h1>
		<p>
			<label for="userId">UserId:&nbsp;</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit" value="Search">
			<br/>
		</p>
	</form>
	<br/>
	<div id="recipeCreate"><a href="recipeCreate.jsp?userId=${userId}">Add Recipe</a></div>
	<br/>
	<h1>Matching Recipes</h1>
        <table border="1">
            <tr>
                <th>RecipeName</th>
                <th>Desciption</th>
                <th>Created</th>
                <th>Cooking Time</th>
                <th>UserName</th>
            </tr>
            <c:forEach items="${recipes}" var="recipe">
                <tr>
                    <td><c:out value="${recipe.getPostName()}" /></td>
                    <td><c:out value="${recipe.getDescription()}" /></td>
                    <td><c:out value="${recipe.getCookingTime()}"/></td>
                    <td><fmt:formatDate value="${recipe.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${recipe.getExperienced().getUserName()}"/></td>
                    <td><a href="RecipeUpdate?recipeId=<c:out value="${recipe.getRecipeId()}"/>">Update</a></td>
                    <td><a href="RecipeDelete?recipeId=<c:out value="${recipe.getRecipeId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
		
</body>
</html>