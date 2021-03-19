package fr.eni.siteencheres.dal;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAO();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAO();
	}

}