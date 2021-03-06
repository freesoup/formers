<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/login.css">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<title>Create a new Account</title>
</head>
<body>
	<div class='page-container newacc-container'>
		<h1 class='pagetitle'>Create your account</h1>
		<div class='form-container newacc-form'>
			<form id='signup-form' action='accountcreated' method='post'>
				<% String error=(String)request.getAttribute("error");
				if (error!=null) {
					out.println("<p class='error'>" + error + "</p>");
				}%>
				<label for="username">Your Username</label> <br> <input
					type="text" name="newuser" id="username" autocomplete="off"
					pattern=".{5,}" required
					oninvalid="this.setCustomValidity('Your user username needs to be at least 5 characters long')"
					oninput="this.setCustomValidity('')"><br> <label
					for="password"> Password </label> <br> <input type="password"
					name="newpassword" id="password" autocomplete="off" pattern=".{5,}"
					required
					oninvalid="this.setCustomValidity('Your user password needs to be at least 5 characters long')"
					oninput="this.setCustomValidity('')"><br>
				<div class='btn-container'>
					<button type="submit" form="signup-form" value="Create new account">Create
						new account</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>