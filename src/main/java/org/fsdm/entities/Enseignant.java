package org.fsdm.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PROF")
public class Enseignant extends Adherent {

	private String departement;

	public Enseignant(){

	}

	public Enseignant(String departement) {
		super();
		this.departement = departement;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public Enseignant(Long idAdherent, String username, String password, boolean actived, String nom, String prenom,
			String telephone, String departement) {
		super(idAdherent, username, password, actived, nom, prenom, telephone);
		this.departement = departement;
	}

	
	


	
	
}