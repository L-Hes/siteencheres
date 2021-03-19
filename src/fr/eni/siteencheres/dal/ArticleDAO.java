/**
 * Authors : Boularouah Samira, Heslot Lisa
 */
package fr.eni.siteencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.siteencheres.bo.Article;
import fr.eni.siteencheres.bo.Retrait;
import fr.eni.siteencheres.bo.Utilisateur;

public class ArticleDAO {
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, image=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? WHERE no_article=?";
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article=?";
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String LISTER_ENCHERES_ENCOURS = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE ? BETWEEN date_debut_encheres AND date_fin_encheres";
	private static final String LISTER_ENCHERES_NOM = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE nom_article= ? AND no_categorie=? AND ? BETWEEN date_debut_encheres AND date_fin_encheres";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) values(?, ?, ?, ?)";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private static final String ARTICLE_DETAIL = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE no_article=?";
	private static final String RETRAIT_DETAIL = "SELECT * FROM RETRAITS WHERE no_article=?";
	private static final String LISTER_ENCHERES_MES = "SELECT * FROM ENCHERES JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE encheres.no_utilisateur=? AND ? BETWEEN date_debut_encheres AND date_fin_encheres";
	private static final String LISTER_VENTES_ENCOURS = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE articles_vendus.no_utilisateur=? AND ? BETWEEN date_debut_encheres AND date_fin_encheres";
	private static final String LISTER_VENTES_NONDEBUTEES = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE articles_vendus.no_utilisateur=? AND ? < date_debut_encheres";
	private static final String LISTER_VENTES_TERMINEES = "SELECT * FROM ARTICLES_VENDUS JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE articles_vendus.no_utilisateur=? AND ? > date_fin_encheres";
	private static final String ENCHERIR = " update ARTICLES_VENDUS set  prix_vente =?   where no_article=?";
	private static final String AJOUTER_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,default,?)";
	private static final String RECUPERER_UTILSATEUR_AVANT_REMBOURSEMENT = "SELECT encheres.no_utilisateur, montant_enchere, credit FROM encheres JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur WHERE no_article = ? ORDER BY date_enchere";
	private static final String REMBOURSER = "UPDATE utilisateurs SET credit=? WHERE no_utilisateur =?";
	private static final String RECUPERER_CREDIT_AVANT_DEBITER = "select credit from utilisateurs where no_utilisateur = ?";;
	private static final String DEBITER = " update utilisateurs set   credit=?  where no_utilisateur =?";
	private static final String MONTANT_ENCHERES_MAX = "SELECT  max(e.montant_enchere)FROM utilisateurs u JOIN  ENCHERES e ON u.no_utilisateur= e.no_utilisateur JOIN  ARTICLES_VENDUS a on a.no_article = e.no_article WHERE ? > date_fin_encheres AND a.no_article = ?";
	private static final String LISTER_ENCHERES_TERMINEE = "SELECT *FROM utilisateurs u JOIN  ENCHERES e ON u.no_utilisateur= e.no_utilisateur JOIN  ARTICLES_VENDUS a on a.no_article = e.no_article WHERE ? > date_fin_encheres AND a.no_article = ?  and montant_enchere = ?";
	private static final String MES_ENCHERES = "SELECT encheres.no_article, encheres.montant_enchere FROM ENCHERES JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE encheres.no_utilisateur=?";
	private static final String TOUTES_ENCHERES = "SELECT montant_enchere, pseudo FROM encheres JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur WHERE no_article = ?";
	private static final String PSEUDO_ACQUEREUR = "SELECT utilisateurs.pseudo FROM utilisateurs JOIN encheres ON utilisateurs.no_utilisateur = encheres.no_utilisateur WHERE encheres.no_article = ? ORDER BY montant_enchere DESC ";

	public void insert(Article article, Retrait retrait) throws SQLException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setString(3, article.getImage());
			pstmt.setDate(4, Date.valueOf(article.getDate_debut_encheres()));
			pstmt.setDate(5, Date.valueOf(article.getDate_fin_encheres()));
			pstmt.setInt(6, article.getPrix_initial());
			pstmt.setInt(7, article.getPrix_initial());
			pstmt.setObject(8, article.getVendeur().getId());
			pstmt.setInt(9, article.getNo_categorie());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
				// INSERT RETRAIT
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, id);
				pstmt.setString(2, retrait.getRue());
				pstmt.setString(3, retrait.getCode_postal());
				pstmt.setString(4, retrait.getVille());
				
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(int no_article, Article article, Retrait retrait) throws SQLException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setString(3, article.getImage());
			pstmt.setDate(4, Date.valueOf(article.getDate_debut_encheres()));
			pstmt.setDate(5, Date.valueOf(article.getDate_fin_encheres()));
			pstmt.setInt(6, article.getPrix_initial());
			pstmt.setInt(7, article.getPrix_initial());
			pstmt.setObject(8, article.getVendeur().getId());
			pstmt.setInt(9, article.getNo_categorie());
			pstmt.setInt(10, no_article);
			
			pstmt.executeUpdate();
			// INSERT RETRAIT
			pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCode_postal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, article.getNo_article());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int no_article) throws SQLException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, no_article);
			pstmt.executeUpdate();
			pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, no_article);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Article> listerEncheresEnCours(LocalDate dateDuJour) {
		List<Article> encheresEnCours = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_ENCHERES_ENCOURS);
			pstmt.setDate(1, Date.valueOf(dateDuJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));

				encheresEnCours.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresEnCours;
	}

	public List<Article> listerEncheresSelonNom(LocalDate dateDuJour, String nom, String categorie) {
		System.out.println("DAO début " + nom);
		List<Article> encheresEnCours = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_ENCHERES_NOM);
			pstmt.setDate(3, Date.valueOf(dateDuJour));
			pstmt.setString(1, nom);
			pstmt.setString(2, categorie);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));
				
				encheresEnCours.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresEnCours;
	}

	public List<Article> listerEncheresMes(int id, LocalDate dateDuJour) {
		List<Article> encheresMes = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_ENCHERES_MES);
			pstmt.setInt(1, id);
			pstmt.setDate(2, Date.valueOf(dateDuJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));
				
				encheresMes.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresMes;
	}

	public List<Article> listerVentesEnCours(int id, LocalDate dateDuJour) {
		List<Article> encheresMes = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_VENTES_ENCOURS);
			pstmt.setInt(1, id);
			pstmt.setDate(2, Date.valueOf(dateDuJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));

				encheresMes.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresMes;
	}

	public List<Article> listerVentesNonDebutees(int id, LocalDate dateDuJour) {
		List<Article> encheresMes = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_VENTES_NONDEBUTEES);
			pstmt.setInt(1, id);
			pstmt.setDate(2, Date.valueOf(dateDuJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));

				encheresMes.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresMes;
	}

	public List<Article> listerVentesTerminees(int id, LocalDate dateDuJour) {
		List<Article> encheresMes = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_VENTES_TERMINEES);
			pstmt.setInt(1, id);
			pstmt.setDate(2, Date.valueOf(dateDuJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));

				encheresMes.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresMes;
	}

	public Article articleDetail(int no_article) {
		Article article = new Article();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(ARTICLE_DETAIL);
			pstmt.setInt(1, no_article);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Utilisateur vendeur = new Utilisateur();

				vendeur.setId(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCode_postal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMot_de_passe(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur((Boolean) rs.getBoolean("administrateur"));

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setVendeur(vendeur);
				article.setNo_categorie(rs.getInt("no_categorie"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	public Retrait retraitDetail(int no_article) {
		Retrait retrait = new Retrait();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(RETRAIT_DETAIL);
			pstmt.setInt(1, no_article);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrait;
	}

	public void encherirArticle(int proposition, int no_article) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(ENCHERIR);
			pstmt.setInt(1, proposition);
			pstmt.setInt(2, no_article);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterEnchere(int no_utilisateur, int no_article, LocalDateTime dateEtHeureDeLenchere,
			int proposition) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(AJOUTER_ENCHERE);
			pstmt.setInt(1, no_utilisateur);
			pstmt.setInt(2, no_article);
			// pstmt.setTimestamp(3,Timestamp.valueOf(dateEtHeureDeLenchere));
			pstmt.setInt(3, proposition);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rembourser(int no_article) {
		int id;
		int credit;
		int montant_enchere;
		int nouveauCredit = 0;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(RECUPERER_UTILSATEUR_AVANT_REMBOURSEMENT);
			pstmt.setInt(1, no_article);

			ResultSet rs = pstmt.executeQuery();
			// récupère le num utilisateur del'enchérisseur précedent, le montant de son
			// enchère et son crédit actuel
			if (rs.next() == true) {
				id = rs.getInt(1);
				montant_enchere = rs.getInt(2);
				credit = rs.getInt(3);

				// rembourser l'encherisseur précédent
				nouveauCredit = (credit + montant_enchere);
				Utilisateur utilisateur = new Utilisateur();
				pstmt = cnx.prepareStatement(REMBOURSER);
				pstmt.setInt(1, nouveauCredit);
				pstmt.setInt(2, id);

				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void debiter(int no_utilisateur, int proposition) {
		int id;
		int credit;
		int nouveauCredit = 0;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(RECUPERER_CREDIT_AVANT_DEBITER);
			pstmt.setInt(1, no_utilisateur);

			ResultSet rs = pstmt.executeQuery();
			// récupère le credit de l'enchérisseur
			rs.next();
			credit = rs.getInt(1);

			// Débite l'encherisseur
			nouveauCredit = (credit - proposition);
			pstmt = cnx.prepareStatement(DEBITER);
			pstmt.setInt(1, nouveauCredit);
			pstmt.setInt(2, no_utilisateur);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Article> listerEncheresRemportees(int id, LocalDate dateDuJour) {
		int enchereMax;
		int enchereFaite;
		int noArticle;
		List<Article> encheresRemportees = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(MES_ENCHERES);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {

				noArticle = rs.getInt(1);
				enchereFaite = rs.getInt(2);
	
				// récupére le montant max de l'article mis en enchére
				PreparedStatement pstmt2 = cnx.prepareStatement(MONTANT_ENCHERES_MAX);
				pstmt2.setDate(1, Date.valueOf(dateDuJour));
				pstmt2.setInt(2, noArticle);

				ResultSet rs2 = pstmt2.executeQuery();

				rs2.next();
				enchereMax = rs2.getInt(1);
				rs2.close();
				pstmt2.close();

				if (enchereFaite == enchereMax) {
					PreparedStatement pstmt3 = cnx.prepareStatement(LISTER_ENCHERES_TERMINEE);
					pstmt3.setDate(1, Date.valueOf(dateDuJour));
					pstmt3.setInt(2, noArticle);
					pstmt3.setInt(3, enchereMax);
					ResultSet rs3 = pstmt3.executeQuery();

					rs3.next();
					Article article = new Article();
					Utilisateur vendeur = new Utilisateur();

					vendeur.setId(rs3.getInt("no_utilisateur"));
					vendeur.setPseudo(rs3.getString("pseudo"));
					vendeur.setNom(rs3.getString("nom"));
					vendeur.setPrenom(rs3.getString("prenom"));
					vendeur.setEmail(rs3.getString("email"));
					vendeur.setTelephone(rs3.getString("telephone"));
					vendeur.setRue(rs3.getString("rue"));
					vendeur.setCode_postal(rs3.getString("code_postal"));
					vendeur.setVille(rs3.getString("ville"));
					vendeur.setMot_de_passe(rs3.getString("mot_de_passe"));
					vendeur.setCredit(rs3.getInt("credit"));
					vendeur.setAdministrateur(false);

					article.setNo_article(rs3.getInt("no_article"));
					article.setNom_article(rs3.getString("nom_article"));
					article.setDescription(rs3.getString("description"));
					article.setImage(rs3.getString("image"));
					article.setDate_debut_encheres(rs3.getDate("date_debut_encheres").toLocalDate());
					article.setDate_fin_encheres(rs3.getDate("date_fin_encheres").toLocalDate());
					article.setPrix_initial(rs3.getInt("prix_initial"));
					article.setPrix_vente(rs3.getInt("prix_vente"));
					article.setVendeur(vendeur);
					article.setNo_categorie(rs3.getInt("no_categorie"));
					
					encheresRemportees.add(article);
					rs3.close();
					pstmt3.close();}};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresRemportees;
	}

	public List<String> listerToutesEncheres(int no_article) {
		List<String> toutesEncheres = new ArrayList<>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_ENCHERES);
			pstmt.setInt(1, no_article);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String enchere = rs.getString("pseudo") + " : " + rs.getInt("montant_enchere") + " points";
				toutesEncheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toutesEncheres;
	}

	public String recupererAcquereur(int no_article) {
		String pseudoAcquereur = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(PSEUDO_ACQUEREUR);
			pstmt.setInt(1, no_article);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			pseudoAcquereur = rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pseudoAcquereur;
	}
}
