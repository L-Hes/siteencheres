package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.siteencheres.bll.ArticleManager;
import fr.eni.siteencheres.bll.ModifUtilisateurManager;

/**
 * Servlet implementation class ServletReinitialiserPassword
 * Authors : Boularouah Samira, Heslot Lisa
 */
@WebServlet("/reinitialiserpassword")
public class ServletReinitialiserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getAttribute("erreur");
		request.getServletContext().getRequestDispatcher("/WEB-INF/reinitialiserpassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mot_de_passe = request.getParameter("mot_de_passe");
		String confirmation = request.getParameter("confirmation");
		String email = request.getParameter("email");
		String erreur = null;
		
		if (mot_de_passe.equals(confirmation)) {
			ModifUtilisateurManager utilisateurManager = ModifUtilisateurManager.getInstance();
			utilisateurManager.reinitialiserPassword(mot_de_passe, email);
			response.sendRedirect("connexion");
		} else {
			erreur = "Mots de passe différents";
			request.setAttribute("erreur", erreur);
			request.getServletContext().getRequestDispatcher("/WEB-INF/reinitialiserpassword.jsp").forward(request, response);
		}
	}
}
