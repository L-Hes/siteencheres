package fr.eni.siteencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Utilisateur;

public class UtilisateurDAO {
	private static final String INSERT_INSCRIPTION = "insert into utilisateurs( pseudo,nom, prenom,email ,telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?) ";
	private static final String VERIF_PSEUDO = "select * from utilisateurs where pseudo=?";
	private static final String VERIF_EMAIL = "select * from utilisateurs where email=?";
	private static final String VERIF_CONNECTION_EMAIL = "select email, mot_de_passe from utilisateurs where email=? and mot_de_passe=?";
	private static final String VERIF_CONNECTION_PSEUDO = "select pseudo, mot_de_passe from utilisateurs where pseudo=? and mot_de_passe=?";
	private static final String RECUPERER_UTILISATEUR_PAR_EMAIL = "select * from utilisateurs where email=?";
	private static final String RECUPERER_UTILISATEUR_PAR_PSEUDO = "select * from utilisateurs where pseudo=?";
	private static final String MODIFIER_PROFIL = " update utilisateurs set pseudo=?,nom=? ,  prenom=?,  email=?,  telephone=?, rue=?,  code_postal=?,  ville=?,  mot_de_passe=?,  credit=? ,  administrateur=?  where email=?";
	private static final String SUPPRIMER_PROFIL = "delete from utilisateurs where email=?";
	private static final String REINITIALISER_PASSWORD = "update utilisateurs set mot_de_passe=? where email=?";
	private static final String LISTER_UTILISATEURS = "select * from utilisateurs";

	public void insert(Utilisateur utilisateur) throws SQLException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_INSCRIPTION, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.getAdministrateur());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// verifie que le pseudo d'inscription est unique et donc n'existe pas en BDD
	public boolean verifPseudo(String pseudo) {
		boolean verifPseudo = true;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_PSEUDO);
			pstmt.setString(1, pseudo);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				verifPseudo = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verifPseudo;
	}

	// verifie que l'email d'inscription est unique et donc n'existe pas en BDD
	public boolean verifEmail(String email) {
		boolean verifEmail = true;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				verifEmail = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verifEmail;
	}

	// verifie que l'identifiant(email) et le mot de passe de connexion
	// appartiennent bien à un utilisateur
	public boolean verifConnectionEmail(String identifiant, String mot_de_passe) {
		boolean verifConnexionEmail = false;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_CONNECTION_EMAIL);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, mot_de_passe);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				verifConnexionEmail = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verifConnexionEmail;
	}

	// verifie que l'identifiant(pseudo) et le mot de passe de connexion
	// appartiennent bien à un utilisateur
	public boolean verifConnectionPseudo(String identifiant, String mot_de_passe) {
		boolean verifConnexionPseudo = false;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_CONNECTION_PSEUDO);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, mot_de_passe);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				verifConnexionPseudo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verifConnexionPseudo;
	}

	// Récupère l'utilisateur pour la connexion avec email
	public Utilisateur connexionUtilisateurEmail(String identifiant) {
		Utilisateur utilisateur = new Utilisateur();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(RECUPERER_UTILISATEUR_PAR_EMAIL);
			pstmt.setString(1, identifiant);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				utilisateur.setId(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	// Récupère l'utilisateur pour la connexion avec pseudo
	public Utilisateur connexionUtilisateurPseudo(String identifiant) {
		Utilisateur utilisateur = new Utilisateur();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(RECUPERER_UTILISATEUR_PAR_PSEUDO);
			pstmt.setString(1, identifiant);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				utilisateur.setId(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	public void modifierUtilisateur(Utilisateur utilisateur) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(MODIFIER_PROFIL);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.getAdministrateur());
			pstmt.setString(12, utilisateur.getEmail());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void supprimerCompte(String mail) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SUPPRIMER_PROFIL);
			pstmt.setString(1, mail);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void desactiverCompte(String mail) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_EMAIL);
			pstmt.setString(1, mail);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("no_utilisateur");
			pstmt = cnx.prepareStatement("DELETE FROM encheres WHERE no_utilisateur=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt = cnx.prepareStatement("SELECT no_article FROM articles_vendus WHERE no_utilisateur=?");
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no_article = rs.getInt("no_article");
				pstmt = cnx.prepareStatement("DELETE FROM retraits WHERE no_article=?");
				pstmt.setInt(1, no_article);
				pstmt.executeUpdate();
			}
			pstmt = cnx.prepareStatement("DELETE FROM articles_vendus WHERE no_utilisateur=?");
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reinitialiserPassword(String password, String email) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(REINITIALISER_PASSWORD);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Utilisateur> listerUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_UTILISATEURS);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setId(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				utilisateurs.add(utilisateur);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}
}
