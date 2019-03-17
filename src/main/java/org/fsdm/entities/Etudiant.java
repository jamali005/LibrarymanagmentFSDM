package org.fsdm.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ETDS")
public class Etudiant extends Adherent {
	private String branche;
	private String section;
	private String semestre;

	public Etudiant(){

	}
	
	public Etudiant(Long idAdherent, String username, String password, boolean actived, String nom, String prenom,
			String telephone, String branche, String section, String semestre) {
		super(idAdherent, username, password, actived, nom, prenom, telephone);
		this.branche = branche;
		this.section = section;
		this.semestre = semestre;
	}




	public String getBranche() {
		return branche;
	}

	public void setBranche(String branche) {
		this.branche = branche;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	

}