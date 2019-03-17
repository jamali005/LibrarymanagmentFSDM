package org.fsdm.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("PFEL")
public class PFELicence extends Document {
	private String anneeUniv;
	private String departemnet;
	private String encadrant;
	private String etudiant;

	public PFELicence(){

	}

	public String getAnneeUniv() {
		return anneeUniv;
	}

	
	
	public PFELicence(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite,
			String anneeUniv, String departemnet, String encadrant, String etudiant) {
		super(numDocum, titre, etat, photo, dateAcquisition, quantite);
		this.anneeUniv = anneeUniv;
		this.departemnet = departemnet;
		this.encadrant = encadrant;
		this.etudiant = etudiant;
	}

	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}

	public String getDepartemnet() {
		return departemnet;
	}

	public void setDepartemnet(String departemnet) {
		this.departemnet = departemnet;
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

	
}