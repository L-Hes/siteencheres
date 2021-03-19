package fr.eni.siteencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.siteencheres.bll.ModifUtilisateurManager;
import fr.eni.siteencheres.bo.Utilisateur;
import fr.eni.siteencheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletModifierUtilisateur Authors : Boularouah
 * Samira, Heslot Lisa
 */
@WebServlet("/modifierUtilisateur")
public class ServletModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getSession().getAttribute("utilisateur");
		request.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ModifUtilisateurManager modifUtilisateur = new ModifUtilisateurManager();
		HttpSession session = request.getSession();
		// récupère la session
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		String mail = utilisateur.getEmail();
		String choixUtilisateur = request.getParameter("choixUtilisateur");
		String mdpActuRenseigne = request.getParameter("motDePasseActuel");
		String mdpActu = utilisateur.getMot_de_passe();
		String supprimerCompte = null;
		String compteModifier = null;
		String erreurMdp = null;
		String erreurMdpActuel = null;
		// supprime le compte utilisateur
		if (choixUtilisateur.equals("supprimer")) {
			// indique à l'utilisateur que le compte est bien supprimé
			supprimerCompte = " Votre compte à bien été supprimé";
			request.setAttribute("supprimerCompte", supprimerCompte);
			// coupe la session
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/WEB-INF/encheres.jsp").forward(request, response);
			modifUtilisateur.supprimerCompte(mail);
		} else {

			// Modifie le compte utilisateur
			if (choixUtilisateur.equals("enregistrer")) {

				String pseudo = request.getParameter("pseudo");
				String Nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = utilisateur.getEmail();
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String code_postal = request.getParameter("code_postal");
				String ville = request.getParameter("ville");
				String mot_de_passe = request.getParameter("motDePasse");
				String verifMotDePasse = request.getParameter("verifMotDePasse");
				int credit = (int) utilisateur.getCredit();
				boolean administrateur = utilisateur.getAdministrateur();

				if (mdpActu.equals(mdpActuRenseigne)) {
					if ((mot_de_passe.equals(verifMotDePasse))) {
						// envoie les modifications à la BDD
						Utilisateur utilisateurUpdate = ModifUtilisateurManager.modifierUtilisateur(pseudo, Nom, prenom,
								email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur);
						compteModifier = "Votre compte a bien été modifié";
						request.setAttribute("compteModifier", compteModifier);
						session.setAttribute("utilisateur", utilisateurUpdate);
						request.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp")
						.forward(request, response);
					} else {
						erreurMdp = "Veuillez saisir deux mots de passe identiques";
						request.setAttribute("erreurMdp", erreurMdp);
						request.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp")
								.forward(request, response);
					}
				} else {
					erreurMdpActuel = " Veuillez saisir le bon mot de passe Actuel";
					request.setAttribute("erreurMdp", erreurMdpActuel);
					request.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp")
							.forward(request, response);
				}
			}

		}

	}
}
