package fr.eni.siteencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.siteencheres.bll.ConnexionManager;
import fr.eni.siteencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion Authors : Boularouah Samira,
 * Heslot Lisa
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("identifiantenMemoire")) {
					request.setAttribute("identifiantenMemoire", cookie.getValue());
				} else if (cookie.getName().equals("mot_de_passeEnMemoire")) {
					request.setAttribute("mot_de_passeEnMemoire", cookie.getValue());
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ConnexionManager connexionManager = new ConnexionManager();

		boolean vIdentifiant = false;
		boolean vConnexion = false;
		String erreur1 = null;
		String connexionOK = "off";

		String identifiant = request.getParameter("Identifiant");
		String mot_de_passe = request.getParameter("mot_de_passe");
		String enMemoire = request.getParameter("enMemoire");

		// verifie si l'identifiant est un email
		if (connexionManager.verifIdentifiant(identifiant) == true) {
			vIdentifiant = true;
			// verifie si l'email et le Mdp sont dans la BDD d'un seul utilisateur!
			if (connexionManager.verifConnectionEmail(identifiant, mot_de_passe) == true) {

				HttpSession session = request.getSession();
				Utilisateur utilisateur = connexionManager.connexionUtilisateurEmail(identifiant);
				session.setAttribute("utilisateur", utilisateur);

				connexionOK = "on";
				session.setAttribute("connexionOK", connexionOK);

				// Cookie en mémoire
				if (enMemoire != null) {
					Cookie[] cookies = request.getCookies();
					Cookie identifiantenMemoire = new Cookie("identifiantenMemoire", identifiant);
					Cookie mot_de_passeEnMemoire = new Cookie("mot_de_passeEnMemoire", mot_de_passe);
					response.addCookie(identifiantenMemoire);
					response.addCookie(mot_de_passeEnMemoire);

				}
				response.sendRedirect("encheres");
			} else {
				erreur1 = "Veuillez entrer un identifiant et/ou un mot de passe valide";
				request.setAttribute("erreur1", erreur1);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
				rd.forward(request, response);
			}
		} else {
			// verifie si le Pseudo et le Mdp sont dans la BDD d'un seul utilisateur!
			if (connexionManager.verifConnectionPseudo(identifiant, mot_de_passe) == true) {
				vConnexion = true;

				// MAJ 10/03 essai session mémoire
				HttpSession session = request.getSession();
				Utilisateur utilisateur = connexionManager.connexionUtilisateurPseudo(identifiant);
				session.setAttribute("utilisateur", utilisateur);

				connexionOK = "on";
				session.setAttribute("connexionOK", connexionOK);

				// Cookie en mémoire
				if (enMemoire != null) {
					Cookie[] cookies = request.getCookies();
					Cookie identifiantenMemoire = new Cookie("identifiantenMemoire", identifiant);
					Cookie mot_de_passeEnMemoire = new Cookie("mot_de_passeEnMemoire", mot_de_passe);
					response.addCookie(identifiantenMemoire);
					response.addCookie(mot_de_passeEnMemoire);
				}
				response.sendRedirect("encheres");
			}
			else {
				erreur1 = "Veuillez entrer un identifiant et/ou un mot de passe valide";
				request.setAttribute("erreur1", erreur1);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
				rd.forward(request, response);
			}
		}
	}
}
