/**
 * Authors : Boularouah Samira, Heslot Lisa
 */
package fr.eni.siteencheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Retrait;
import fr.eni.siteencheres.bo.Utilisateur;
import fr.eni.siteencheres.dal.ArticleDAO;
import fr.eni.siteencheres.dal.DAOFactory;
import fr.eni.siteencheres.dal.UtilisateurDAO;

public class ArticleManager {

	private ArticleDAO articleDAO;
	private static ArticleManager instance = null;

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	private ArticleManager(ArticleDAO articleDAO) {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	private ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		this.articleDAO = articleDAO;
	}

	public void ajouterArticle(String nom_article, String description, String image, LocalDate date_debut_encheres,
			LocalDate date_fin_encheres, int prix_initial, int prix_vente, Utilisateur vendeur, int no_categorie,
			String rue, String code_postal, String ville) throws SQLException {
		Article article = new Article();
		article.setNom_article(nom_article);
		article.setDescription(description);
		article.setImage(image);
		article.setDate_debut_encheres(date_debut_encheres);
		article.setDate_fin_encheres(date_fin_encheres);
		article.setPrix_initial(prix_initial);
		article.setPrix_vente(prix_initial);
		article.setVendeur(vendeur);
		article.setNo_categorie(no_categorie);
		Retrait retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCode_postal(code_postal);
		retrait.setVille(ville);
		articleDAO.insert(article, retrait);
	}

	public void modifierArticle(int no_article, String nom_article, String description, String image,
			LocalDate date_debut_encheres, LocalDate date_fin_encheres, int prix_initial, int prix_vente,
			Utilisateur vendeur, int no_categorie, String rue, String code_postal, String ville) throws SQLException {
		Article article = new Article();
		article.setNom_article(nom_article);
		article.setDescription(description);
		article.setImage(image);
		article.setDate_debut_encheres(date_debut_encheres);
		article.setDate_fin_encheres(date_fin_encheres);
		article.setPrix_initial(prix_initial);
		article.setPrix_vente(prix_initial);
		article.setVendeur(vendeur);
		article.setNo_categorie(no_categorie);
		Retrait retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCode_postal(code_postal);
		retrait.setVille(ville);
		articleDAO.update(no_article, article, retrait);
	}

	public void supprimerArticle(int no_article) throws SQLException {
		System.out.println("Article manager n : " + no_article);
		articleDAO.delete(no_article);
	}

	public List<Article> listerEncheresEnCours() {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerEncheresEnCours(dateDuJour);
	}

	public List<Article> listerEncheresSelonNom(String nom, String categorie) {
		LocalDate dateDuJour = LocalDate.now();
		System.out.println("Manager " + nom);
		return DAOFactory.getArticleDAO().listerEncheresSelonNom(dateDuJour, nom, categorie);
	}

	public List<Article> listerEncheresMes(int id) {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerEncheresMes(id, dateDuJour);
	}

	public List<Article> listerVentesEnCours(int id) {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerVentesEnCours(id, dateDuJour);
	}

	public List<Article> listerVentesNonDebutees(int id) {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerVentesNonDebutees(id, dateDuJour);
	}

	public List<Article> listerVentesTerminees(int id) {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerVentesTerminees(id, dateDuJour);
	}

	public Article articleDetail(int no_article) {
		return DAOFactory.getArticleDAO().articleDetail(no_article);
	}

	public Retrait retraitDetail(int no_article) {
		return DAOFactory.getArticleDAO().retraitDetail(no_article);
	}

	public void encherir(int proposition, int no_article) {
		articleDAO.encherirArticle(proposition, no_article);
	}

	public void ajouterEnchere(int no_utilisateur, int no_article, int proposition) {
		LocalDateTime dateEtHeureDeLenchere = LocalDateTime.now();
		articleDAO.ajouterEnchere(no_utilisateur, no_article, dateEtHeureDeLenchere, proposition);
	}

	public void rembourser(int no_article) {
		articleDAO.rembourser(no_article);
	}

	public void debiter(int no_utilisateur, int proposition) {
		articleDAO.debiter(no_utilisateur, proposition);
	}

	public List<Article> listerEncheresRemportees(int id) {
		LocalDate dateDuJour = LocalDate.now();
		return DAOFactory.getArticleDAO().listerEncheresRemportees(id, dateDuJour);
	}

	public List<String> listerToutesEncheres(int no_article) {
		return DAOFactory.getArticleDAO().listerToutesEncheres(no_article);
	}

	public String recupererAcquereur(int no_article) {
		return DAOFactory.getArticleDAO().recupererAcquereur(no_article);
	}
}
