package org.fsdm.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ADMIN")
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="TYPE_ROLE",discriminatorType=DiscriminatorType.STRING,length=6)
public class RespBib extends Users {
  
	private String nom;
	private String prenom;
	@OneToMany(mappedBy="respBib",fetch=FetchType.LAZY)
	private Collection<Document> documents;
	private Adherent adherent;

	public RespBib(){

	}
	
	public RespBib(Long idAdherent, String username, String password, boolean actived,String nom,
			String prenom) {
		super(idAdherent, username, password, actived);
		
		this.nom = nom;
		this.prenom = prenom;
	}

	
	

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adherent getadherent() {
		return adherent;
	}

	public void setadherent(Adherent adherent) {
		this.adherent = adherent;
	}

	

}