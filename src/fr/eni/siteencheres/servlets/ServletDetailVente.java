package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.siteencheres.bll.ArticleManager;
import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente Authors : Boularouah Samira,
 * Heslot Lisa
 */
@WebServlet("/detailvente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		ArticleManager articleManager = ArticleManager.getInstance();
		HttpSession session = request.getSession();
		session.setAttribute("articleDetail", articleManager.articleDetail(no_article));
		session.getAttribute("articleDetail");

		Article article = (Article) session.getAttribute("articleDetail");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		LocalDate fin_enchere = article.getDate_fin_encheres();
		LocalDate debut_enchere = article.getDate_debut_encheres();
		String pseudoVendeur = article.getVendeur().getPseudo();
		String telephoneVendeur = article.getVendeur().getTelephone();
		request.setAttribute("telephoneVendeur", telephoneVendeur);
		LocalDate dateDuJour = LocalDate.now();
		String pseudoAcquereur = articleManager.recupererAcquereur(no_article);
		boolean vendeur = false;
		boolean acquereur = false;
		boolean encherEncour = true;
		boolean modifier = false;
		List<String> listeEncheres = articleManager.listerToutesEncheres(no_article);
		request.setAttribute("encherEncour", encherEncour);
		System.out.println(listeEncheres);

		if (dateDuJour.isBefore(debut_enchere)) {
			String pseudoUtilisateur = utilisateur.getPseudo();
			if (pseudoVendeur.equals(pseudoUtilisateur)) {
				modifier = true;
				request.setAttribute("modifier", modifier);
			}
		}

		if (dateDuJour.isAfter(fin_enchere)) {
			String pseudoUtilisateur = utilisateur.getPseudo();
			if (pseudoVendeur.equals(pseudoUtilisateur)) {
				vendeur = true;
				acquereur = false;
				encherEncour = false;
				request.setAttribute("vendeur", vendeur);
				request.setAttribute("encherEncour", encherEncour);
				request.setAttribute("acquereur", acquereur);
				request.setAttribute("pseudoAcquereur", pseudoAcquereur);
				request.setAttribute("toutesEncheres", listeEncheres);
				System.out.println(pseudoAcquereur);
				

			} else {
				acquereur = true;
				vendeur = false;
				encherEncour = false;
				request.setAttribute("acquereur", acquereur);
				request.setAttribute("encherEncour", encherEncour);
				request.setAttribute("vendeur", vendeur);

			}

		}
		session.setAttribute("articleDetail", articleManager.articleDetail(no_article));
		session.getAttribute("articleDetail");
		request.setAttribute("retraitDetail", articleManager.retraitDetail(no_article));
		request.getAttribute("retraitDetail");
		request.getAttribute("utilisateur");
		request.setAttribute("toutesEncheres", articleManager.listerToutesEncheres(no_article));
		request.getAttribute("toutesEncheres");
		request.getServletContext().getRequestDispatcher("/WEB-INF/detailvente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
