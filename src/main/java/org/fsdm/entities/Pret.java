package org.fsdm.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "pret")
public class Pret implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_PRET")
	private Long numPret;
	@Column(name="DATE_RETOURTH")
	private Date dateRetourThe;
	@Column(name="DATE_PRET")
	private Date datePret;
	@Column(name="DATE_RETOURN")
	private Date dateRetourn;
	@ManyToOne
	@JoinColumn(name="ID_Document")
	private Document document;
	@ManyToOne
    @JoinColumn(name="ID_Adherent")
	private Adherent adherent;
    
	public Pret(){

	}
    
	public Pret(Date datePret, Document document, Adherent adherent) {
		super();
		this.datePret = datePret;
		this.document = document;
		this.adherent = adherent;
	}


	public Long getNumPret() {
		return numPret;
	}


	public Date getDateRetourThe() {
		return dateRetourThe;
	}


	public void setDateRetourThe(Date dateRetourThe) {
		this.dateRetourThe = dateRetourThe;
	}


	public Date getDatePret() {
		return datePret;
	}


	public void setDatePret(Date datePret) {
		this.datePret = datePret;
		
	}


	public Date getDateRetourn() {
		return dateRetourn;
	}


	public void setDateRetourn(Date dateRetourn) {
		this.dateRetourn = dateRetourn;
	}

	
	public Document getDocument() {
		return document;
	}

	
	public void setDocument(Document document) {
		this.document = document;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "pret", cascade = CascadeType.ALL)
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

}