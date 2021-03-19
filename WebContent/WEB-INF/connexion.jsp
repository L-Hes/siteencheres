<!--Authors : Boularouah Samira, Heslot Lisa-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Connexion</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="container-fluid">
				<div id="navbarNav">
					<a class="navbar-brand" href="${session.getContextPath()}encheres"><h1>
							<img src="css/applehorse.png" alt="AppleHorse enchères"
								class="logo-mini">AppleHorse
						</h1></a>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<section class="container-fluid">
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<form action="connexion" method="POST">

						<table id="champsConnexion" width="100%">
							<tr>
								<td><label for="email">Identifiant :</label></td>
								<td><input type="text" id="Identifiant" name="Identifiant"
									value="${ identifiantenMemoire }" /></td>
							</tr>
							<tr>
								<td><label for="mot_de_passe">Mot de passe:</label></td>
								<td><input type="password" id="mot_de_passe"
									name="mot_de_passe" value="${ mot_de_passeEnMemoire }" /></td>
							</tr>
						</table>
						<table>
							<tr>
								<td width="50%"><input type="submit" value="Connexion">
								</td>
								<td><input type="checkbox" value="enMemoire" id="enMemoire"
									name="enMemoire"> <label for="enMemoire">Se
										souvenir de moi</label><br> <a href="reinitialiserpassword">Mot
										de passe oublié</a></td>
							</tr>
						</table>
						<div class="d-grid gap-2">

							<button type="button" class="btn btn-outline-danger"
								id="boutonInscription">
								<a href="inscription">Créer un compte</a>
							</button><br>
						</div>
					</form>
				</div>
				<div class="col-lg-4"></div>
			</div>
			<c:if test="${requestScope.erreur1 != null }">
			<div class="d-grid gap-2 col-6 mx-auto">
				<button class="btn btn-danger" type="button" disabled>${requestScope.erreur1}</button>
			</div>
			</c:if>
		</section>
	</main>
</body>
</html>