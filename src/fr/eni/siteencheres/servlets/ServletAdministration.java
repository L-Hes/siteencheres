package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.siteencheres.bll.ModifUtilisateurManager;


/**
 * Authors : Boularouah Samira, Heslot Lisa
 */
@WebServlet("/administration")
public class ServletAdministration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ModifUtilisateurManager utilisateurManager = ModifUtilisateurManager.getInstance();
		
		request.setAttribute("utilisateurs", utilisateurManager.listerUtilisateurs());
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ModifUtilisateurManager utilisateurManager = ModifUtilisateurManager.getInstance();
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		System.out.println(action);
		
		if(action.equalsIgnoreCase("supprimer")) {
			System.out.println(action);
			utilisateurManager.supprimerCompte(email);
		} else if (action.equalsIgnoreCase("desactiver")) {
			System.out.println(action);
			utilisateurManager.desactiverCompte(email);
		}
		
		response.sendRedirect("administration");
	}

}