package org.fsdm.metier;

import java.util.Date;
import java.util.List;

import org.fsdm.entities.Adherent;
import org.fsdm.entities.Categorie;
import org.fsdm.entities.Document;
import org.fsdm.entities.Livre;
import org.fsdm.entities.Mediatheque;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.Pret;
import org.fsdm.entities.Reservation;
import org.fsdm.entities.TheseDoctorat;
import org.springframework.data.domain.Page;

public interface IBibliothequeMetier {
	//#################### GSTION DES PRETS ET RESERVATIONS ####################
	public void addPret(Long codeAdh,Long idDoc);
	public void returnPret(Long numPret,Long codeAdh,Long idDoc);
	public void reservation(Long idAdhe,Long idDoc);
	public void addLivre(Livre livre);
	public Page<Pret> listePretAretourn(int page, int size);
	public Page<Pret> listePretNoir(int page, int size);
	public Page<Pret> listePretNR(int page, int size);
	public Page<Reservation> listeReservation(int page,int size);
	public Page<Pret> listePret(int page,int size);
	public void annulReservation( Long idReserv, Long idDoc);
	public void validReservation(Long codeAdh, Long idDoc);
	public void deletReservation(Long idReservation);
	public boolean expirationDelaiResev(Date dateRese);
	public Date expirationDelaiPret(Date datePret,Long idAdher);
	
	//####################### GESTION DES DOCUMENTS ####################

	public void addPFE(PFELicence pfe);
	public void addMaster(MemoMaster master);
	public void addDoctorat(TheseDoctorat doctorat);
	public void addMedia(Mediatheque mediatheque);
	public  Document consulterUnDoc(Long idDoc);
	public Pret consulterUnPret(Long numPret);
	public  Livre consulterUnLivre(Long isbn);
	public  PFELicence consulterUneLicence(Long id);
	public  MemoMaster consulterUnMemo(Long id);
	public  TheseDoctorat consulterUnDoctorat(Long id);
	public Categorie consulterCategorie(Long idcategorie);
	public void addLivreToCategorie(Long idCat,Long idDoc);
	public Page<Livre> consulterDesLivres(String mc,int page,int size);
//	public Page<Document> consulterDesDoc(int page,int size); 
	public Page<PFELicence> consultLicences(String mc,int page,int size);
	public Page<MemoMaster> consultMasters(String mc,int page,int size);
	public Page<TheseDoctorat> consultDoctoras(String mc,int page,int size);
	public void addCategorie(Categorie categorie);
	
	//################### GESTION DES ADHERENTS ############################
	
	public Page<Adherent> listeAdherent(int page,int size);
	public Adherent consulterAdherent(Long idAdhe);

	
	

}
