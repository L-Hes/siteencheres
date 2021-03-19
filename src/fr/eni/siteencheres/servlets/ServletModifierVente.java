package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.siteencheres.bll.ArticleManager;
import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 * authors : Boularouah Samira, Heslot Lisa
 */
@WebServlet("/modifiervente")
public class ServletModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		ArticleManager articleManager = ArticleManager.getInstance();
		request.setAttribute("articleDetail", articleManager.articleDetail(no_article));
		request.getAttribute("articleDetail");
		request.setAttribute("retraitDetail", articleManager.retraitDetail(no_article));
		request.getAttribute("retraitDetail");
		request.getAttribute("utilisateur");
		request.getServletContext().getRequestDispatcher("/WEB-INF/modifiervente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleManager articleManager = ArticleManager.getInstance();
		Utilisateur vendeur =   (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		// Article
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		String nom_article = request.getParameter("nom_article");
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		LocalDate date_debut_encheres = LocalDate.parse(request.getParameter("date_debut_encheres"));
		LocalDate date_fin_encheres = LocalDate.parse(request.getParameter("date_fin_encheres"));
		int prix_initial = Integer.parseInt(request.getParameter("prix_initial"));
		int prix_vente = Integer.parseInt(request.getParameter("prix_initial"));
		int no_categorie = Integer.parseInt(request.getParameter("no_categorie"));
				
		// Retrait
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
				
		try {
			articleManager.modifierArticle(no_article, nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, vendeur, no_categorie, rue, code_postal, ville);
			response.sendRedirect("encheres");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}