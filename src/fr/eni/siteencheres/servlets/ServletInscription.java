package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.siteencheres.bll.InscriptionManager;

/**
 * Servlet implementation class ServletInscription
 * Authors : Boularouah Samira
 */
@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		InscriptionManager inscriptionManager = new InscriptionManager();
		request.setCharacterEncoding("UTF-8");
		boolean vDoubleMp = false;
		boolean vPseudo = false;
		boolean vEmail = false;
		String erreur = null;
		String erreur2 = null;
		String erreur3 = null;
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("motDePasse");
		String verifMotDePasse = request.getParameter("verifMotDePasse");
		int credit = 0;
		boolean administrateur=false;
		
		if(inscriptionManager.verifDoublePassword(mot_de_passe , verifMotDePasse)== true ) {
			vDoubleMp=true;}
			else {
				erreur="Veuillez entrer deux mot de passe identiques";
			}
		
		if(inscriptionManager.verifPseudo(pseudo)== true ) {
			vPseudo=true;}
			else {
				erreur2="Pseudo déjà utilisé";
			}
		
		if(inscriptionManager.verifEmail(email)== true ) {
			vEmail=true;}
			else {
				erreur3="Email déjà utilisé";
			}
		
		if(vDoubleMp==true && vPseudo==true && vEmail==true) {
			try {
				inscriptionManager.ajouterUtilisateur(pseudo,nom, prenom,email,telephone, rue,code_postal,ville, mot_de_passe,credit,administrateur);
				response.sendRedirect("encheres");
			} catch ( SQLException e) {
			
				e.printStackTrace();
			}
		}
		else {
			request.getSession().setAttribute("pseudo", pseudo);
			request.getSession().setAttribute("nom", nom);
			request.getSession().setAttribute("prenom", prenom);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("telephone", telephone);
			request.getSession().setAttribute("rue", rue);
			request.getSession().setAttribute("code_postal", code_postal);
			request.getSession().setAttribute("ville", ville);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			request.setAttribute("erreur", erreur);
			request.setAttribute("erreur2", erreur2);
			request.setAttribute("erreur3", erreur3);
			
			rd.forward(request, response);
			;
			}
		}
	}