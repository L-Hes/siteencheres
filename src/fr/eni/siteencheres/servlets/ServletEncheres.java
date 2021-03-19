package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class AccueilServlet
 * Authors : Boularouah Samira, Heslot Lisa
 */
@WebServlet("/encheres")
public class ServletEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Récupérer toutes les enchères en cours
		ArticleManager articleManager = ArticleManager.getInstance();
		String nomArticle = request.getParameter("nomArticle");
		String categorie = request.getParameter("categorie");
		request.getSession().getAttribute("utilisateur");
		request.getSession().getAttribute("connexionOK");
		
		if (nomArticle != null) {
			request.setAttribute("encheresAfficher", articleManager.listerEncheresSelonNom(nomArticle, categorie));
		} else {
			request.setAttribute("encheresAfficher", articleManager.listerEncheresEnCours());
		}
			
		request.getServletContext().getRequestDispatcher("/WEB-INF/encheres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleManager articleManager = ArticleManager.getInstance();
		String achatVente = request.getParameter("achatVente");
		request.getSession().getAttribute("utilisateur");
		int id = Integer.parseInt(request.getParameter("id"));
		boolean proprietaire =  false;
		boolean enchOuverte =  false;
		boolean enchEnCours =  false;
		boolean enchRemportee =  false;
		boolean venEnCours =  false;
		boolean venNonDebutee =  false;
		boolean ventTerminee = false;
		
		if (achatVente != null) {
			if (achatVente.equals("encheresOuvertes")) {
				request.setAttribute("encheresAfficher", articleManager.listerEncheresEnCours());
				enchOuverte=true;
				request.setAttribute("enchOuverte", enchOuverte);
				
			} else if (achatVente.equals("encheresEnCours")) {
				request.setAttribute("encheresAfficher", articleManager.listerEncheresMes(id));
				enchEnCours=true;
				request.setAttribute("enchEnCours", enchEnCours);
				
			} else if (achatVente.equals("encheresRemportees")) {
				request.setAttribute("encheresAfficher", articleManager.listerEncheresRemportees(id));
				proprietaire= true;
				enchRemportee=true;
				request.setAttribute("enchRemportee", enchRemportee);
				request.setAttribute("proprietaire", proprietaire);
				
			} else if (achatVente.equals("ventesEnCours")) {
				request.setAttribute("encheresAfficher", articleManager.listerVentesEnCours(id));
				venEnCours=true;
				request.setAttribute("venEnCours", venEnCours);
				
			} else if (achatVente.equals("ventesNonDebutees")) {
				request.setAttribute("encheresAfficher", articleManager.listerVentesNonDebutees(id));
				venNonDebutee=true;
				request.setAttribute("venNonDebutee", venNonDebutee);
				
			} else if (achatVente.equals("ventesTerminees")){
				request.setAttribute("encheresAfficher", articleManager.listerVentesTerminees(id));
				ventTerminee=true;
				request.setAttribute("ventTerminee", ventTerminee);
			} else {
				request.setAttribute("encheresAfficher", articleManager.listerEncheresEnCours());
			
			}
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/encheres.jsp").forward(request, response);
	}
}
