package fr.eni.siteencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.siteencheres.bll.ArticleManager;
import fr.eni.siteencheres.bll.ModifUtilisateurManager;
import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEnchérir
 * Authors : Boularouah Samira, Heslot Lisa
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getSession().getAttribute("articleDetail");
		request.getServletContext().getRequestDispatcher("/WEB-INF/detailvente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String enchereOK= "Enchère accéptée";
		
		ArticleManager articleManager = ArticleManager.getInstance();
		HttpSession session = request.getSession();
		
		//récupère l'utilisateur qui enchéri
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int no_utilisateur= utilisateur.getId();
		int credit = utilisateur.getCredit();
		System.out.println("récupére l'utilisateur qui enchéri");
		System.out.println(utilisateur);
		System.out.println("num utilisateur : " + no_utilisateur);
		System.out.println("credit : " + credit);
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		
		System.out.println("récupère l'article en parametre");
		System.out.println(" no_article : " + no_article);
		// récupère la proposition d'un utilisateur
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		System.out.println("récupère la proposition ");
		System.out.println(" proposition : " + proposition);
		
		// Si le crédit est suffisant
		if (proposition<= credit) {
			// modfie le prix actuel en prix enchéri
			    articleManager.encherir(proposition,no_article);
					
				//Rembourse l'encherrisseur précedent
					articleManager.rembourser(no_article);
		
				// créer une enchère dans la table encheres
					articleManager.ajouterEnchere(no_utilisateur,no_article,proposition );
					
				//Débite le nouveau encherisseur
					articleManager.debiter(no_utilisateur, proposition);
			
			request.setAttribute("enchereOK",enchereOK );
			response.sendRedirect("encheres");}

	}

}
