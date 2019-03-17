package org.fsdm.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Adherent extends Users implements Serializable {
  
	private String nom;
	private String prenom;
	private String telephone;
	@OneToMany(mappedBy="adherent",fetch=FetchType.LAZY)
	private Collection<Pret> pret;

	private Reservation reservation;
	
	public Adherent(){

	}

	public Adherent(Long idAdherent, String username, String password, boolean actived, String nom, String prenom,
			String telephone) {
		super(idAdherent, username, password, actived);
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
	}

	public Collection<Pret> getPret() {
		return pret;
	}

	public void setPrets(Collection<Pret> pret) {
		this.pret = pret;
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


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public Date DemendeExtention(){
		return null;
	}

	/**
	 * 
	 * @param numDoc
	 */
	public int reserve(int numDoc){
		return 0;
	}

	public Date demendeExtention(){
		return null;
	}
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}