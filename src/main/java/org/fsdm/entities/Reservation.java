package org.fsdm.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reservation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_RESERV")
	private Long numReservation;
	private Date dateReservation;
	@OneToOne
	@JoinColumn(name="ID_Document")
	private Document document;
	@OneToOne
	@JoinColumn(name="ID_Adherent")
	private Adherent adherent;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reservation(Date dateReservation, Document document, Adherent adherent) {
		super();
		this.dateReservation = dateReservation;
		this.document = document;
		this.adherent = adherent;
	}

	public Reservation(Long numReservation, Date dateReservation) {
		super();
		this.numReservation = numReservation;
		this.dateReservation = dateReservation;
	}

	public Long getNumReservation() {
		return numReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
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
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation", cascade = CascadeType.ALL)
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	
}

