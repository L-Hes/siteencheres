<!-- MAJ 09/03 16:15 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- CSS et bootstrap -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
	
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link href="css/main.css" rel="stylesheet">
	<title>Enchères</title>
</head>
<body>

<header>
	
	
	<div class="header">
    <div class="top-header">
      <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-faded">
          <a class="navbar-brand" href="${session.getContextPath()}encheres"><h1><img src="css/applehorse.png" alt="AppleHorse enchères" class="logo-mini">AppleHorse</h1></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    <ul class="navbar-nav mr-auto">
		    <c:if test = "${sessionScope.connexionOK != 'on'}">
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}connexion">
		          S'inscrire/Se connecter
		          </a>
		      </li>
		     </c:if>
		
		      <c:if test = "${sessionScope.connexionOK == 'on'}">    
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}encheres">
		          Enchères
		          </a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}nouvellevente">
		          Vendre un article
		          </a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}profil">
		          Mon profil
		          </a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}deconnexion">
		          Déconnexion
		          </a>
		      </li>
		      <c:if test="${ utilisateur.administrateur == true }">
		      <li class="nav-item">
		        <a class="nav-link" href="${session.getContextPath()}administration">
		          Administration
		          </a>
		      </li>
		      </c:if>
		     </c:if>
		    </ul>
          </div>
          		  	<span class="navbar-text">
				    	Bonjour ${utilisateur.pseudo } !
			      	</span>
          
        </nav>
      </div>
    </div>
  </div>
</header>
	
	<main>
		<h2>Liste des enchères</h2>
			<c:if test="${requestScope.supprimerCompte != null }">
				<div class="d-grid gap-2 col-6 mx-auto">
					<button class="btn btn-danger" type="button" disabled>${requestScope.supprimerCompte}</button><br>
				</div>
			</c:if>
		<!-- Fonction recherche -->
		<section id="rechercher" class="container">
				<form action="encheres" method="GET">
				<div class="row">
					<div class="col-12 col-lg-5 bloc-recherche">
						<label>Filtres :</label><br>
						<input class="form-control me-2" type="search" placeholder="Nom de l'article" aria-label="Search" id="nomArticle" name="nomArticle">
						<label for="categorie">Catégorie : </label>
						<select id="categorie" name="categorie">
						  <option value="1">Informatique</option>
						  <option value="2">Ameublement</option>
						  <option value="3">Vêtement</option>
						  <option value="4">Sport et Loisirs</option>
						</select>
					</div>
					<div class="col-12 col-lg-5 align-self-center">
						<div class="d-grid gap-2"><input type="submit" class="btn btn-lg btn-outline-danger" value="Rechercher" id="boutonRechercher"></div>
					</div>
				</div>
				</form>
				<c:if test = "${sessionScope.connexionOK == 'on'}">
				<div class="row">
					<div class="col-12 col-lg-5 bloc-recherche">
					
					<h3>Afficher seulement : </h3>
							<form action="encheres" method="POST">
							<input type="hidden" value="${utilisateur.id }" name="id">
							<select id="achatVente" name="achatVente">
							 <optgroup label="Achats">
							    <option value="encheresOuvertes">Enchères ouvertes</option>
							    <option value="encheresEnCours">Mes enchères en cours</option>
							    <option value="encheresRemportees">Mes enchères remportées</option>
							  </optgroup>
							  <optgroup label="Mes ventes">
							    <option value="ventesEnCours">Mes ventes en cours</option>
							    <option value="ventesNonDebutees">Ventes non débutées</option>
							    <option value="ventesTerminees">Ventes terminées</option>
							  </optgroup>
							  </select>
								<input type="submit" class="btn btn-lg btn-outline-danger" value="Afficher" id="boutonAfficher">
  							</form>
					
					</div>
				</div>
				</c:if>
			
		</section>
		<section>
			<div id="encheresEnCours" class="container">
				<div class="row">
				   <c:if test= "${ enchOuverte ==true}">
				    <h2>Les Enchères ouvertes</h2>
				    </c:if>
				    <c:if test= "${ enchEnCours ==true}">
				    <h2>Mes Enchères en cours</h2>
				    </c:if>
				    <c:if test= "${ enchRemportee ==true}">
				    <h2>Mes Enchères Remportées</h2>
				    </c:if>
				    <c:if test= "${ venEnCours ==true}">
				    <h2>Mes Ventes en cours</h2>
				    </c:if>
				    <c:if test= "${ venNonDebutee ==true}">
				    <h2>Mes Ventes non débutées</h2>
				    </c:if>
				    <c:if test= "${ ventTerminee ==true}">
				    <h2>Mes Ventes terminées</h2>
				    </c:if>
				    <c:forEach items="${ encheresAfficher }" var="article">
				    <div class="col-12 col-lg-6">
						<div class="card mb-3">
						  <div class="row g-0">
						    <div class="col-md-4">
						      <img src="${ article.image }" alt="${ article.nom_article }" class="imageMiniature">
						    </div>
						    <div class="col-md-8">
						      <div class="card-body">
						        <form action="detailvente" method="GET"><input type="hidden" name="no_article" value="${ article.no_article }"><h3>${ article.nom_article }</h3><input type="submit" class="btn btn-lg btn-outline-danger" value="Détail"></form>
						        <p class="card-text">Prix : ${ article.prix_vente } points</p>
						        <p class="card-text">Fin de l'enchère : ${ article.date_fin_encheres }</p>
						        <c:if test = "${proprietaire == true }">
						        <p class="card-text">Propriétaire : ${ article.vendeur.pseudo }</p>
						        </c:if>
						        <c:if test = "${proprietaire != true }">
						        <p class="card-text">Vendeur : ${ article.vendeur.pseudo }</p>
						        </c:if>
						      </div>
						    </div>
						  </div>
						</div>
				    </div>
				    </c:forEach>
				  </div>
  			</div>
		</section>
		
	</main>
</body>
</html>