<!--Authors : Boularouah Samira, Heslot Lisa-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS et bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<link href="css/main.css" rel="stylesheet">
<title>MonProfil</title>
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
		<h2>Mon profil</h2>
		<section class="container-fluid">
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<table id="profil">
						<tr>
							<td>Pseudo :</td>
							<td>${utilisateur.pseudo }</td>

						</tr>
						<tr>
							<td>Nom:</td>
							<td>${utilisateur.nom}</td>

						</tr>
						<tr>
							<td>Prénom :</td>
							<td>${utilisateur.prenom }</td>

						</tr>
						<tr>
							<td>Email :</td>
							<td>${utilisateur.email}</td>

						</tr>
						<tr>
							<td>Telephone :</td>
							<td>${utilisateur.telephone }</td>

						</tr>
						<tr>
							<td>Rue :</td>
							<td>${utilisateur.rue }</td>

						</tr>
						<tr>
							<td>Code postal :</td>
							<td>${utilisateur.code_postal }</td>

						</tr>
						<tr>
							<td>Ville :</td>
							<td>${utilisateur.ville }</td>
						</tr>
					</table>
					<br/>
					<button type="button" class="btn btn-outline-danger" id="boutonModifierUtilisateur"><a href="modifierUtilisateur">Modifier</a></button>
					
				</div>
				
			</div>
			
			<div class="col-lg-4"></div>
			<c:if test="${requestScope.compteModifier != null }">
				<div class="d-grid gap-2 col-6 mx-auto"><br>
					<button class="btn btn-danger" type="button" disabled>${requestScope.compteModifier}</button><br>
				</div>
			</c:if>
			
		</section>
	

	</main>
</body>
</html>