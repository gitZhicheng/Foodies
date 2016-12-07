<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Searching Result</title>
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

	<h1>Matching Recipes</h1>
        <table border="1">
            <tr>
                <th>RecipeName</th>
                <th>Description</th>
                <th>Cooking Time</th>
                <th>Created At</th>
            </tr>
            <c:forEach items="${recipes}" var="recipe">
                <tr>
               		<td><a href="RecipeInfo?recipeId=<c:out value="${recipe.getRecipeId()}"/>">${recipe.getPostName()}</a></td>
                    <td><c:out value="${recipe.getDescription()}" /></td>
                    <td><c:out value="${recipe.getCookingTime()}"/></td>
                    <td><fmt:formatDate value="${recipe.getCreated()}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
       </table>
		
</body>
</html>
