/**
 * Heslot Lisa
 */
package fr.eni.siteencheres.bo;

import java.time.LocalDate;

public class Article {
	
	private int no_article;
	private String nom_article;
	private String description;
	private String image;
	private LocalDate date_debut_encheres;
	private LocalDate date_fin_encheres;
	private int prix_initial;
	private int prix_vente;
	private Utilisateur vendeur;
	private int no_categorie;
	
	public Article() {
		
	}
	
	public Article(String nom_article, String description, String image, LocalDate date_debut_encheres, LocalDate date_fin_encheres,
			int prix_initial, Utilisateur vendeur, int no_categorie) {
		this.nom_article = nom_article;
		this.description = description;
		this.image = image;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.vendeur = vendeur;
		this.no_categorie = no_categorie;
	}
	
	
	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(LocalDate date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public LocalDate getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(LocalDate date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}
	public int getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}
	public int getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
		
}
