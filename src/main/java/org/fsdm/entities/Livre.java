package org.fsdm.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("LVRE")
public class Livre extends Document {
	private Long isbn;
	private String auteur;
	private String editeur;
	
	public Livre(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite, Long isbn,
			String auteur, String editeur) {
		super(numDocum, titre, etat, photo, dateAcquisition, quantite);
		this.isbn = isbn;
		this.auteur = auteur;
		this.editeur = editeur;
	}


	public String getAuteur() {
		return auteur;
	}
	

	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	
	
	
}