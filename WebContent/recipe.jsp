<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Recipe Info</title>

	<!-- Bootstrap styles -->
	<link href="assets/css/bootstrap.css" rel="stylesheet"/>
	<!-- Customize styles -->
	<link href="style.css" rel="stylesheet"/>
	<!-- font awesome styles -->
	<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">
		<!--[if IE 7]>
		<link href="css/font-awesome-ie7.min.css" rel="stylesheet">
	<![endif]-->
	
	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	<!-- Favicons -->
	<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>

<body>

<div class="container">
<div id="gototop"> </div>
<header id="header">
</header>
<!--
Navigation Bar Section 
-->

<div class="navbar">
  <div class="navbar-inner">
	<div class="container">
	  <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	  </a>
	  <div class="nav-collapse">
		<ul class="nav">
		  <li class="navbar-brand"><a style="font-size:17px;color:#0269c2" href="index.jsp">Foodies	</a></li>
		  <li class=""><a href="list-view.html">List View</a></li>
		  <li class=""><a href="grid-view.html">Grid View</a></li>
		</ul>
		<form action="" class="navbar-search pull-left">
		  <input type="text" placeholder="Search Recipes" class="search-query span2">
		</form>
	  </div>
	</div>
  </div>
</div>

<div class="row">
  <div class="span12">
  	<ul class="breadcrumb cuisinetype">
  	  <li>
  	    <a href="#">Home</a>
  	  </li>
  	  <c:forEach items="${cuisineNames}" var="cn">
  	  	<li>
  	    <span class="divider">/</span>
  	    <a href="#">${cn}</a>
  	  </li>
  	  </c:forEach>
  	</ul>
  	<div class="well well-small">
  	  <div class="row-fluid recipe">
  		<div class="span5">
  			<h2 class="recipe-title">${recipe.postName}</h2>
  			<div class="rating">
  			  <p>Stars : 4.0 out of 5</p>
  			  <p>113 reviews | 56 made it</p>
  			</div>
  			<div class="recipe-desc">
  			  <span>Recipe by : <a>${recipe.user==null? "Anonymous":recipe.user.userName}</a></span>
  			  <p>"${recipe.description}"</p>
  			</div>
  		</div>
  		<div class="span7">
  			<div class="recipe-image">
  			  <img src="${recipe.image}">
  			</div>
  		</div>
  		<div class="span12">
  		  <div class="recipe-navbar">
  		    <button class="shopBtn btn-block">Made It</button>
  		    <button class="shopBtn btn-block">Rate It</button>
  		    <button class="shopBtn btn-block">Comment</button>
  		    <button class="shopBtn btn-block">Share</button>
  		  </div>
  		  <div class="recipe-info span5">
  		    <h3 class="recipe-info-header">Ingredients</h3>
  		  	<c:forEach items="${ingrs}" var="ingr">
  		    <ul>
  		  	  <li><c:out value="${ingr}" /></li>
  		    </ul>
  		  	</c:forEach>
  		  </div>
  		  <div class="recipe-info span5">
  		    <h3 class="recipe-info-header">Steps</h3>
  		    <c:forEach items="${steps}" var="s">
  		    <ul>
  		  	  <li><c:out value="${s}" /></li>
  		    </ul>
  		  	</c:forEach>
  		  </div>
  		</div>
  	  </div>
  	</div>
  </div>
</div>

</div>

</body>
</html>