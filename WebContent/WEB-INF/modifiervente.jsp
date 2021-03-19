<!-- Authors : Boularouah Samira, Heslot Lisa -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<!-- CSS et bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
<title>Modifier la vente</title>
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
<body>
	<main>
		<h2>Modifier la vente</h2>
		<section class="container-fluid">
			<div class="row">
				<div class="col-12 col-lg-4">
					<img src="${ articleDetail.image }" alt="img" class="imageDetail">
				</div>
				<div class="col-12 col-lg-5">
					<form action="modifiervente" method="POST">
						<table>
							<tr>
								<td><label for="nom_article">Article : </label></td>
								<td><input type="text" id="nom_article" name="nom_article" value="${ articleDetail.nom_article }">
								<input type="hidden" name="no_article" value="${ articleDetail.no_article }"></td>
							</tr>
							<tr>
								<td><label for="description">Description : </label></td>
								<td><textarea id="description" name="description">${ articleDetail.description }</textarea></td>
							</tr>
							<tr>
								<td><label for="no_categorie">Catégorie : </label></td>
								<td><select id="no_categorie" name="no_categorie">
						  		<option value="1">Informatique</option>
						  		<option value="2">Ameublement</option>
						  		<option value="3">Vêtement</option>
						  		<option value="4">Sport et Loisirs</option>
								</select></td>
							</tr>
							<tr>
								<td><label for="image">Photo de l'article : </label></td>
								<td><input type="url" id="image" name="image" value="${ articleDetail.image }"></td>
							</tr>
							<tr>
								<td><label for="prix_initial">Mise à prix : </label></td>
								<td><input type="number" id="prix_initial" name="prix_initial" min="1" max="5000" value="${ articleDetail.prix_initial }"></td>
							</tr>
							<tr>
								<td><label for="date_debut_encheres">Début de l'enchère : </label></td>
								<td><input type="date" id="date_debut_encheres" name="date_debut_encheres" value="${ articleDetail.date_debut_encheres }"></td>
							</tr>
							<tr>
								<td><label for="date_fin_encheres">Fin de l'enchère : </label></td>
								<td><input type="date" id="date_fin_encheres" name="date_fin_encheres" value="${ articleDetail.date_fin_encheres }"></td>
							</tr>
						</table>
						<fieldset>
    						<legend>Retrait</legend>
    						<table>
    							<tr>
    								<td><label for="rue">Rue : </label></td>
    								<td><input type="text" id="rue" name="rue" value="${utilisateur.rue}"></td>
    							</tr>
    							<tr>
    								<td><label for="code_postal">Code postal : </label></td>
    								<td><input type="text" id="code_postal" name="code_postal" value="${utilisateur.code_postal}"></td>
    							</tr>
    							<tr>
    								<td><label for="ville">Ville : </label></td>
    								<td><input type="text" id="ville" name="ville" value="${sessionScope.utilisateur.ville}"></td>
    							</tr>
    						</table>
    					</fieldset>
    					<div class="row" id="boutonInscription">
							<div class="col-12 col-lg-6"><div class="d-grid gap-2"><input type="submit" class="btn btn-outline-danger" value="Enregistrer"/></div></div>
							<div class="col-12 col-lg-6"><div class="d-grid gap-2"><button type="button" class="btn btn-outline-danger"><a href="${session.getContextPath()}detailvente">Annuler</a></button></div></div>
						</div>
					</form>
				</div>
			</div>
		</section>
	</main>
</body>
</html>