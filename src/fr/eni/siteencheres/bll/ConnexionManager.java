/**
 * Authors : Boularouah Samira, Heslot Lisa
 */

package fr.eni.siteencheres.bll;

import fr.eni.siteencheres.bo.Utilisateur;
import fr.eni.siteencheres.dal.UtilisateurDAO;

public class ConnexionManager {
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	private static ConnexionManager instance = null;

	public static ConnexionManager getInstance() {
		if (instance == null) {
			instance = new ConnexionManager();
		}
		return instance;
	}

	// Verifie si l'identifiant saisie par l'utilisateur est un email ou un pseudo
	public boolean verifIdentifiant(String identifiant) {
		if (identifiant.contains("@")) {
			return true;
		}
		return false;
	}

	// si l'identifiant est un email on vérifie que les paramêtre saisie par
	// l'utilisateur existe bien en BDD
	public boolean verifConnectionEmail(String identifiant, String mot_de_passe) {
		if (utilisateurDAO.verifConnectionEmail(identifiant, mot_de_passe) == true) {
			return true;
		}
		return false;
	}

	// si l'identifiant est un pseudo on vérifie que les paramêtre saisie par
	// l'utilisateur existe bien en BDD
	public boolean verifConnectionPseudo(String identifiant, String mot_de_passe) {
		if (utilisateurDAO.verifConnectionPseudo(identifiant, mot_de_passe) == true) {
			return true;
		}
		return false;
	}

	public Utilisateur connexionUtilisateurEmail(String identifiant) {
		Utilisateur utilisateur = utilisateurDAO.connexionUtilisateurEmail(identifiant);
		return utilisateur;
	}

	public Utilisateur connexionUtilisateurPseudo(String identifiant) {
		Utilisateur utilisateur = utilisateurDAO.connexionUtilisateurPseudo(identifiant);
		return utilisateur;
	}
}
