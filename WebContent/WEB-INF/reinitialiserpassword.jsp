<!--Authors : Boularouah Samira, Heslot Lisa-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--version1-->	
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<!-- CSS et bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
<title>Réinitialiser le mot de passe</title>
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg navbar-light">
	  <div class="container-fluid">
	    <div class="collapse navbar-collapse" id="navbarNav">
	        <a class="navbar-brand" href="${session.getContextPath()}encheres"><h1><img src="css/applehorse.png" alt="AppleHorse enchères" class="logo-mini">AppleHorse</h1></a>
	    </div>
	  </div>
	</nav>
</header>
<main>
	<section class="container-fluid">	
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<form action="reinitialiserpassword" method="POST">
			
					<table id="champsReinitialisation" width="100%">
						<tr>
						<td><label for="email">Email :</label></td>
						<td><input type="email" id="email" name="email" /></td>
						</tr>
						<tr>
						<td><label for="mot_de_passe">Mot de passe:</label> </td>
						<td><input type="password" id="mot_de_passe" name="mot_de_passe"/></td>
						</tr>
						<tr>
						<td><label for="mot_de_passe">Confirmation:</label> </td>
						<td><input type="password" id="confirmation" name="confirmation"/></td>
						</tr>
					</table>
					<div class="d-grid gap-2"><br>
						<input type="submit" class="btn btn-outline-danger" id="boutonInscription" value="Réinitialiser">
					</div>
				</form>
			</div>
		</div>
			<c:if test="${requestScope.erreur != null }">
			<div class="d-grid gap-2 col-6 mx-auto"><br>
				<button class="btn btn-danger" type="button" disabled>${requestScope.erreur}</button>
			</div>
			</c:if>
	</section>
</main>
</body>
</html>