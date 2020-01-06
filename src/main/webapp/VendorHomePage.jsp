<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Sign In Sign Up Form</title>
		<script type="text/javascript" src="js/custom.js"></script>
       	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="bootstrap-3.4.1-dist/css/bootstrap.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="fontawesome-free-5.9.0-web/css/all.css">
		<link rel="stylesheet" href="css/custom2.css">
	</head>
	<body >
		<%@ include file="VendorHeader.jsp" %>
		<%@ include file="navbar.jsp" %>
		
		<div class="container-fluid vendorhome">
			<div class="container">
				<a href="product" class="btn btn-info vendor-product">Product Page</a>
			</div>
		</div>
		
		<%@ include file="Footer.html" %>
		<%@ include file="LastRow.html" %>

</body>
</html>