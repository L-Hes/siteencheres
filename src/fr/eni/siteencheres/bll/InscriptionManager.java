/**
 * Authors : Boularouah Samira, Heslot Lisa
 */
package fr.eni.siteencheres.bll;

import java.sql.SQLException;

import fr.eni.siteencheres.bo.Utilisateur;
import fr.eni.siteencheres.dal.DAOFactory;
import fr.eni.siteencheres.dal.UtilisateurDAO;

public class InscriptionManager {
	private UtilisateurDAO utilisateurDAO;
	private static InscriptionManager instance = null;

	public static InscriptionManager getInstance() {
		if (instance == null) {
			instance = new InscriptionManager();
		}
		return instance;
	}

	private InscriptionManager(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public InscriptionManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, int credit, boolean administrateur)
			throws SQLException {
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
		utilisateurDAO.insert(utilisateur);
	}

	public boolean verifDoublePassword(String mdp, String vMdp) {
		if (mdp.equals(vMdp)) {
			return true;
		}
		return false;
	}

	public boolean verifPseudo(String pseudo) {
		if (utilisateurDAO.verifPseudo(pseudo) == true) {
			return true;
		}
		return false;
	}

	public boolean verifEmail(String email) {
		if (utilisateurDAO.verifEmail(email) == true) {
			return true;
		}
		return false;
	}
}
