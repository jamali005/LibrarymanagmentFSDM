package org.fsdm.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@DiscriminatorValue("THDC")
public class TheseDoctorat extends Document {
	private String condidat;
	private Date dateSoutnance;
	private String directeurDeRecherche;
	private String laboratoire;
	private String motcle;
	private String resume;
	private String specialite;
	private String typeDoctorat;

	public TheseDoctorat(){

	}

	public TheseDoctorat(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite,
			String condidat, Date dateSoutnance, String directeurDeRecherche, String laboratoire, String motcle,
			String resume, String specialite, String typeDoctorat) {
		super(numDocum, titre, etat, photo, dateAcquisition, quantite);
		this.condidat = condidat;
		this.dateSoutnance = dateSoutnance;
		this.directeurDeRecherche = directeurDeRecherche;
		this.laboratoire = laboratoire;
		this.motcle = motcle;
		this.resume = resume;
		this.specialite = specialite;
		this.typeDoctorat = typeDoctorat;
	}







	public String getCondidat() {
		return condidat;
	}

	public void setCondidat(String condidat) {
		this.condidat = condidat;
	}

	public Date getDateSoutnance() {
		return dateSoutnance;
	}

	public void setDateSoutnance(Date dateSoutnance) {
		this.dateSoutnance = dateSoutnance;
	}

	public String getDirecteurDeRecherche() {
		return directeurDeRecherche;
	}

	public void setDirecteurDeRecherche(String directeurDeRecherche) {
		this.directeurDeRecherche = directeurDeRecherche;
	}

	public String getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(String laboratoire) {
		this.laboratoire = laboratoire;
	}

	public String getMotcle() {
		return motcle;
	}

	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getTypeDoctorat() {
		return typeDoctorat;
	}

	public void setTypeDoctorat(String typeDoctorat) {
		this.typeDoctorat = typeDoctorat;
	}

	
}