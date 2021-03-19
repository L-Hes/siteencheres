<!--Authors : Boularouah Samira, Heslot Lisa-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS et bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
<title>ModifierUtilisateur</title>
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
	<section class="container">
		<div class="container">
		<form action="modifierUtilisateur" method="POST">
			<div class="row">
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="pseudo">Pseudo :</label></td><td width="70%"><input type="text" id="pseudo" name="pseudo" value="${utilisateur.pseudo}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="nom">Nom :</label></td><td width="70%"><input type="text" id="nom" name="nom" value="${utilisateur.nom}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="prenom">Prénom :</label></td><td width="70%"><input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="email">Email :</label></td><td width="70%"><input type="text" id="email" name="email" value="${utilisateur.email}" disabled></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="telephone">Téléphone :</label></td><td width="70%"><input type="text" id="telephone" name="telephone" value="${utilisateur.telephone}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="rue">Rue :</label></td><td width="70%"><input type="text" id="rue" name="rue" value="${utilisateur.rue}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="code_postal">Code postal :</label></td><td width="70%"><input type="text" id="code_postal" name="code_postal"value="${utilisateur.code_postal}"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="ville">Ville :</label></td><td width="70%"><input type="text" id="ville" name="ville" value="${utilisateur.ville}"/></td></tr></table></div>
				
				
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="motDePasseActuel">Mot de passe actuel:</label></td><td width="70%"><input type="password" id="motDePasseActuel" name="motDePasseActuel"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="motDePasse">Nouveau mot de passe:</label></td><td width="70%"><input type="password" id="motDePasse" name="motDePasse"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="verifMotDePasse">Confirmation :</label></td><td width="70%"><input type="password" id="verifMotDePasse" name="verifMotDePasse"/></td></tr></table></div>
				<div class="col-12 col-lg-6" id="champProfil"><table width="100%"><tr><td><label for="verifMotDePasse">Crédits :</label></td><td><input type=text id="credit" name="credit" value="${utilisateur.credit}" disabled></td></tr></table></div>
			</div>
			<div class="row" id="boutonModifierProfil">
				<div class="col-12 col-lg-3"></div>
				<div class="col-12 col-lg-3"><div class="d-grid gap-2"><input type="submit" class="btn btn-outline-danger" value="enregistrer" name ="choixUtilisateur"/></div></div>
				<div class="col-12 col-lg-3"><div class="d-grid gap-2"><input type="submit" class="btn btn-outline-danger" value="supprimer" name ="choixUtilisateur"/></button></div></div>
				<div class="col-12 col-lg-3"></div>
			</div>
		</form>
		</div><br>
			<c:if test="${requestScope.erreurMdp != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.erreurMdp}</button><br>
				</div>
			</c:if>
			<c:if test="${requestScope.erreurMdpActuel != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.erreurMdpActuel}</button>
				</div>
			</c:if>
	</section>
</main>
</body>
</html>