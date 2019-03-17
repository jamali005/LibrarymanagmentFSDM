package org.fsdm.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_DOCUMENT",discriminatorType=DiscriminatorType.STRING)
public class Document implements Serializable {
    @Id 
//  @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="NUM_INVENTAIRE")
	private Long numDocum;
    @NotEmpty
	@Size(min=6,max=250,message="le titre ne répond pas aux critères")
	private String titre;
    @NotEmpty
    private String etat;
    private String photo;
    @Column(name="DATE_ACQUISITION")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateAcquisition;
	private int quantite;
	@OneToMany(mappedBy="document",fetch=FetchType.LAZY)
	private Collection<Pret> pret;
	@OneToOne
	@JoinColumn(name="ID_RESERV")
	private Reservation reservation;
	@ManyToOne
	@JoinColumn(name="ID_RespBib")
	private RespBib respBib;
	@ManyToOne
	@JoinColumn(name="ID_CTEGORIE")
	private Categorie categorie;
 
	public Long getNumDocum() {
		return numDocum;
	}

	
	public void setNumDocum(Long numDocum) {
		this.numDocum = numDocum;
	}


	public Document(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite) {
		super();
		this.numDocum = numDocum;
		this.titre = titre;
		this.etat = etat;
		this.photo = photo;
		this.dateAcquisition = dateAcquisition;
		this.quantite = quantite;
	}


	public Collection<Pret> getPret() {
		return pret;
	}

	public void setPret(Collection<Pret> pret) {
		this.pret = pret;
	}


	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDateAcquisition() {
		return dateAcquisition;
	}
	
	public void setDateAcquisition(Date dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	

	
}