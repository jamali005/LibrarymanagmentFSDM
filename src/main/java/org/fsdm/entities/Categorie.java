package org.fsdm.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CTEGORIE")
	private Long idCategorie;
	private String champDiciplinaire;
	private String cote;
	private String domaine;
	@OneToMany(mappedBy="reservation",fetch=FetchType.LAZY)
	private Collection<Document> documents;
	
	public Categorie(String champDiciplinaire, String cote) {
		super();
		this.champDiciplinaire = champDiciplinaire;
		this.cote = cote;
	}
   
	public Long getIdCategorie() {
		return idCategorie;
	}
	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

	
	

	public Categorie(){

	}

	public String getChampDiciplinaire() {
		return champDiciplinaire;
	}

	public void setChampDiciplinaire(String champDiciplinaire) {
		this.champDiciplinaire = champDiciplinaire;
	}

	public String getcote() {
		return cote;
	}

	public void setcote(String cote) {
		this.cote = cote;
	}

	public String getCote() {
		return cote;
	}

	public void setCote(String cote) {
		this.cote = cote;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}



}