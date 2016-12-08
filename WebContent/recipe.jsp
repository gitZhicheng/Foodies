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

<!-- Modal -->
<div class="modal fade" id="postModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Upload Your Work</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="control-group">
            <label class="control-label" for="postTitle" style="font-weight:bold">Title</label>
            <input type="text" class="form-control span5" id="postTitle">
          </div>
          <div class="control-group">
            <label for="postContent" style="font-weight:bold">Content</label>
            <textarea row="3" id="postContent" class="span5"></textarea>
          </div>
          <div class="control-group">
            <label for="postImage" style="font-weight:bold">Upload Your Photo (Optional)</label>
            <input type="text" class="form-control span5" id="postImage">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Write A Review</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="control-group">
            <label for="rating" style="font-weight:bold">Rating</label>
            <input type="text" class="form-control span1" id="rating"><span style="margin-left:5px"> / 5</span>
          </div>
          <div class="control-group">
            <label for="reviewContent" style="font-weight:bold">Content</label>
            <textarea row="3" class="span5" id="reviewContent"></textarea>
          </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Leave A Comment</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="control-group">
            <label for="commentContent" style="font-weight:bold">Content</label>
            <textarea row="5" class="span5" id="commentContent"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="recModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Recommend To Friends</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="control-group">
            <label for="toUserName" style="font-weight:bold">To Username</label>
            <input type="text" class="form-control span5" id="toUserName">
          </div>
          <div class="control-group">
            <label for="recContent" style="font-weight:bold">Content</label>
            <textarea class="span5" id="recContent"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>

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
  	    <a href="index.jsp">Home</a>
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
  			  <p>Stars : ${avgRating} out of 5</p>
  			  <p><span><a>${reviewCnt} reviews</a></span> | <span><a>${postCnt} made it</a></span></p>
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
  		    <button class="shopBtn btn-block" data-toggle="modal" data-target="#postModal">I Made It</button>
  		    <button class="shopBtn btn-block" data-toggle="modal" data-target="#reviewModal">Rate It</button>
  		    <button class="shopBtn btn-block" data-toggle="modal" data-target="#commentModal">Comment</button>
  		    <button class="shopBtn btn-block" data-toggle="modal" data-target="#recModal">Share</button>
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
<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.easing-1.3.min.js"></script>
    <script src="assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="assets/js/shop.js"></script>
</body>
</html>