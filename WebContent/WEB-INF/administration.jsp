<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
	
	
<!--version1-->	
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<!-- CSS et bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
<title>Administration</title>
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg navbar-light">
	  <div class="container-fluid">
	    <div id="navbarNav">
	        <a class="navbar-brand" href="${session.getContextPath()}encheres"><h1><img src="css/applehorse.png" alt="AppleHorse enchères" class="logo-mini">AppleHorse</h1></a>
	    </div>
	  </div>
	</nav>
</header>
<main>
	<section class="container">
		<div class="row">
			<div class="col col-lg-2">
				<h3>Supprimer un utilisateur</h3>
				<p>Attention, action irréversible<br>
				Entrer son email :</p>
				<form action="administration" method="POST">
				<input type="hidden" value="supprimer" name="action" >
					<input type="email" name="email"><br>
					<input type="submit" class="btn btn-lg btn-outline-danger" value="Supprimer" id="boutonRechercher">
				</form>
				<h3>Désactiver un utilisateur</h3>
				<p>Garde le profil<br>
				Entrer son email :</p>
				<form action="administration" method="POST">
				<input type="hidden" value="desactiver" name="action" >
					<input type="email" name="email"><br>
					<input type="submit" class="btn btn-lg btn-outline-danger" value="Désactiver" id="boutonRechercher">
				</form>
			</div>
			<div class="col col-lg-10">
				<display:table name="utilisateurs" pagesize="10" requestURI = "administration" class="table table-danger table-striped">
				 <display:column property="id" title="n°" />
				 <display:column property="pseudo" title="Pseudo" />
				 <display:column property="nom" title="Nom" />
				 <display:column property="prenom" title="Prénom" />
				 <display:column property="email" title="Email" />
				 <display:column property="telephone" title="Téléphone" />
				 <display:column property="rue" title="Rue" />
				 <display:column property="code_postal" title="Code postal" />
				 <display:column property="ville" title="Ville" />
				 <display:column property="mot_de_passe" title="Mot de passe" />
				 <display:column property="credit" title="Crédits" />
				 <display:column property="administrateur" title="Administrateur" />
				</display:table>
			</div>
		</div>
	</section>
</main>
</body>
</html>