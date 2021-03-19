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
<title>Inscription</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarNav">
					<a class="navbar-brand" href="${session.getContextPath()}encheres"><h1>
							<img src="css/applehorse.png" alt="AppleHorse enchères"
								class="logo-mini">AppleHorse
						</h1></a>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<h2>Mon profil</h2>
		<section class="container">
			<div class="container">
				<form action="inscription" method="POST">
					<div class="row">
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="pseudo">Pseudo :</label></td>
									<td width="70%"><input type="text" id="pseudo"
										name="pseudo" value="${pseudo}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="nom">Nom :</label></td>
									<td width="70%"><input type="text" id="nom" name="nom"
										value="${nom}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="prenom">Prénom :</label></td>
									<td width="70%"><input type="text" id="prenom"
										name="prenom" value="${prenom}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="email">Email :</label></td>
									<td width="70%"><input type="text" id="email" name="email"
										value="${email}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="telephone">Téléphone :</label></td>
									<td width="70%"><input type="text" id="telephone"
										name="telephone" value="${telephone}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="rue">Rue :</label></td>
									<td width="70%"><input type="text" id="rue" name="rue" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="code_postal">Code postal :</label></td>
									<td width="70%"><input type="text" id="code_postal"
										name="code_postal" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="ville">Ville :</label></td>
									<td width="70%"><input type="text" id="ville" name="ville" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="motDePasse">Mot de passe:</label></td>
									<td width="70%"><input type="password" id="motDePasse"
										name="motDePasse" /></td>
								</tr>
							</table>
						</div>
						<div class="col-12 col-lg-6" id="champProfil">
							<table width="100%">
								<tr>
									<td><label for="verifMotDePasse">Confirmation :</label></td>
									<td width="70%"><input type="password"
										id="verifMotDePasse" name="verifMotDePasse" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="row" id="boutonInscription">
						<div class="col-12 col-lg-3"></div>
						<div class="col-12 col-lg-3">
							<div class="d-grid gap-2">
								<input type="submit" class="btn btn-outline-danger"
									value="Créer" />
							</div>
						</div>
						<div class="col-12 col-lg-3">
							<div class="d-grid gap-2">
								<button type="button" class="btn btn-outline-danger"
									id="boutonEncheres">
									<a href="encheres">Annuler</a>
								</button>
							</div>
						</div>
						<div class="col-12 col-lg-3"></div>
					</div>
				</form>
			</div>
			<c:if test="${requestScope.erreur != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.erreur}</button><br>
				</div>
			</c:if>
			<c:if test="${requestScope.erreur2 != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.erreur2}</button><br>
				</div>
			</c:if>
			<c:if test="${requestScope.erreur3 != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.erreur3}</button>
				</div>
			</c:if>
		</section>

	</main>
</body>
</html>

</body>
</html>