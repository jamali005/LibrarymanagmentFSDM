package org.fsdm.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("MEM")
public class MemoMaster extends Document {
	private String anneeUniv;
	private String encadrant;
	private String etudiant;
	private String nomMaster;
	public MemoMaster(){

	}

	public MemoMaster(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite,
			String anneeUniv, String encadrant, String etudiant, String nomMaster) {
		super(numDocum, titre, etat, photo, dateAcquisition, quantite);
		this.anneeUniv = anneeUniv;
		this.encadrant = encadrant;
		this.etudiant = etudiant;
		this.nomMaster = nomMaster;
	}





	public String getAnneeUniv() {
		return anneeUniv;
	}

	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}

	public String getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(String encadrant) {
		this.encadrant = encadrant;
	}

	public String getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}

	public String getNomMaster() {
		return nomMaster;
	}

	public void setNomMaster(String nomMaster) {
		this.nomMaster = nomMaster;
	}

	

}
