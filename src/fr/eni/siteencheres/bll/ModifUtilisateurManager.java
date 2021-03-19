/**
 * Authors : Boularouah Samira, Heslot Lisa
 */
package fr.eni.siteencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Utilisateur;
import fr.eni.siteencheres.dal.DAOFactory;
import fr.eni.siteencheres.dal.UtilisateurDAO;

public class ModifUtilisateurManager {
	private static UtilisateurDAO utilisateurDAO;
	private static ModifUtilisateurManager instance = null;

	public static ModifUtilisateurManager getInstance() {
		if (instance == null) {
			instance = new ModifUtilisateurManager();
		}
		return instance;
	}

	public ModifUtilisateurManager(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public ModifUtilisateurManager() {
		super();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	public static Utilisateur modifierUtilisateur(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String code_postal, String ville, String mot_de_passe, int credit,
			boolean administrateur) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCode_postal(code_postal);
		utilisateur.setVille(ville);
		utilisateur.setMot_de_passe(mot_de_passe);
		utilisateur.setCredit(credit);
		utilisateur.setAdministrateur(administrateur);
		utilisateurDAO.modifierUtilisateur(utilisateur);
		return utilisateur;
	}

	public void supprimerCompte(String mail) {
		utilisateurDAO.supprimerCompte(mail);
	}

	public void desactiverCompte(String mail) {
		utilisateurDAO.desactiverCompte(mail);
	}

	public void reinitialiserPassword(String password, String email) {
		utilisateurDAO.reinitialiserPassword(password, email);
	}

	public List<Utilisateur> listerUtilisateurs() {
		return DAOFactory.getUtilisateurDAO().listerUtilisateurs();
	}

}
