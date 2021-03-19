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
<title>Détail vente</title>
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
		<c:if test="${ vendeur == true && encherEncour != true && pseudoAcquereur == null }">
			<h2>Aucune enchère n'a été effectuée! </h2>
		</c:if>
		<c:if test="${ vendeur == true && pseudoAcquereur !=null}">
			<h2>${pseudoAcquereur} a remporté la vente</h2>
		</c:if>
		<c:if test="${ acquereur == true }">
			<h2>Vous avez remporté la vente</h2>
		</c:if>
		<h2>Détail vente</h2>
		<section class="container-fluid">
			<div class="row">
				<div class="col-12 col-lg-4">
					<img src="${ articleDetail.image }" alt="img" class="imageDetail">
				</div>
				<div class="col-12 col-lg-5">
					<h3>${ articleDetail.nom_article }</h3>
					<form action="encherir" method="POST">
						<table>
							<input type="hidden" name="no_article" value="${ articleDetail.no_article}">
							<tr>
								<td><label for="description">Description : </label></td>
								<td>${ articleDetail.description }</td>
							</tr>
							<c:if test="${encherEncour == true }">
								<tr>
									<td><label for="description">Catégorie : </label></td>
									<td><c:if test="${ articleDetail.no_categorie == 1}">Informatique</c:if>
									<c:if test="${ articleDetail.no_categorie == 2}">Ameublement</c:if>
									<c:if test="${ articleDetail.no_categorie == 3}">Vêtement</c:if>
									<c:if test="${ articleDetail.no_categorie == 4}">Sport et loisirs</c:if></td>
								</tr>
							</c:if>
							<tr>
								<td><label for="meilleureOffre">Meilleure offre :
								</label></td>
								<td>${ articleDetail.prix_vente } points <c:if
										test="${vendeur == true && listeEncheres !=null  }"> par ${ pseudoAcquereur}</c:if></td>
							</tr>
							<tr>
								<td><label for="miseAPrix">Mise à prix : </label></td>
								<td>${ articleDetail.prix_initial } points</td>
							</tr>
							<c:if test="${ vendeur == true && encherEncour == true }">
								<tr>
									<td><label for="retrait">Fin de l'enchère : </label></td>
									<td>${ articleDetail.date_fin_encheres }</td>
								</tr>
							</c:if>
							<tr>
								<td><label for="vendeur">Vendeur : </label></td>
								<td>${ articleDetail.vendeur.pseudo }</td>
							</tr>
							<tr>
								<td><label for="vendeur">Retrait : </label></td>
								<td>${ retraitDetail.rue }<br> ${ retraitDetail.code_postal }
									${ retraitDetail.ville }
								</td>
							</tr>
							<c:if test="${encherEncour == true }">
							<c:if test="${ articleDetail.prix_vente + 1 < utilisateur.credit && utilisateur.id != articleDetail.vendeur.id }">
								<tr>
									<td><label for="proposition">Proposition : </label></td>
									<td><input type="number" name="proposition"
										id="proposition" name="proposition" step="1"
										min="${ articleDetail.prix_vente +1 }"
										value="${ articleDetail.prix_vente + 1 }"><input
										type="submit" value="Enchérir"></td>
								</tr>
							</c:if>
							</c:if>
							<c:if test="${ acquereur == true }">
								<tr>
									<td><label for="description">Téléphone : </label></td>
									<td>${ telephoneVendeur }</td>
								</tr>
							</c:if>
						</table>
						<c:if test="${vendeur == true && pseudoAcquereur != null}">
						<h4>Historique des enchères :</h4>
							<div><c:forEach items="${ toutesEncheres }" var="enchere">
							${ enchere } <br>
							</c:forEach>
							</div>
							<div class="col-12 col-lg-3">
								<div class="d-grid gap-2"><br>
									<button type="button" class="btn btn-danger"
										id="boutonRetrait" disabled>
										<a href="encheres">Retrait effectué</a>
									</button>
								</div>
							</div>
						</c:if>
					</form>
						<c:if
							test="${ modifier == true }">
							<form action="modifiervente" method="GET">
								<input type="hidden" name="no_article"
									value="${ articleDetail.no_article}">
								<div class="d-grid gap-2">
									<input type="submit" class="btn btn-lg btn-outline-danger"
										value="Modifier la vente" id="modifierVente">
								</div>
							</form>
							<form action="annulervente" method="GET">
								<input type="hidden" name="no_article"
									value="${ articleDetail.no_article}">
								<div class="d-grid gap-2">
									<input type="submit" class="btn btn-lg btn-outline-danger"
										value="Annuler la vente" id="annulerVente">
								</div>
							</form>
						</c:if>
						<c:if test="${acquereur == true }">
							<div class="col-12 col-lg-3">
								<div class="d-grid gap-2">
								<br><button type="button" class="btn btn-outline-danger"
										id="boutonRetrait">
										<a href="encheres">Retour</a>
									</button>
								</div>
							</div>
						</c:if>
						<c:if test="${ encherEncour == true && articleDetail.prix_vente + 1 > utilisateur.credit }">
							<div class="d-grid gap-2">
								<button type="button" class="btn btn-danger"
										id="boutonRetrait" disabled>
										Crédits insuffisants
								</button>
							</div>
						</c:if>
						
				</div>
			</div>
		</section>
	</main>
</body>
</html>