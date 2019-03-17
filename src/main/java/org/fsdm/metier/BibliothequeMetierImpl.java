package org.fsdm.metier;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.fsdm.dao.CategorieRepository;
import org.fsdm.dao.DocumentRepository;
import org.fsdm.dao.PretRepository;
import org.fsdm.dao.ReservatioRepository;
import org.fsdm.dao.UsersRepository;
import org.fsdm.entities.Adherent;
import org.fsdm.entities.Categorie;
import org.fsdm.entities.Document;
import org.fsdm.entities.Etudiant;
import org.fsdm.entities.Livre;
import org.fsdm.entities.Mediatheque;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.Pret;
import org.fsdm.entities.Reservation;
import org.fsdm.entities.TheseDoctorat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BibliothequeMetierImpl implements IBibliothequeMetier {
	@Autowired
    private DocumentRepository documentRepository;
	
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private ReservatioRepository reservatioRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	//#################### GSTION DES PRETS ET RESERVATIONS ####################
	@Override
	public void addPret(Long codeAdh, Long idDoc) {
		Document doc=consulterUnDoc(idDoc);
		if(doc==null) throw new RuntimeException("document introuvable");
		Adherent adher=(Adherent) usersRepository.findOne(codeAdh); 
		if(adher==null) throw new RuntimeException("Adherent introuvable");
		Pret pret=new Pret(new Date(), doc, adher);
		pretRepository.save(pret);
		Date dateRetourThe;
		Calendar calendrier = new GregorianCalendar();
		calendrier.setTime(new Date());
		if(adher instanceof Etudiant)
		{
		calendrier.add(Calendar.DATE, 6);
		dateRetourThe = calendrier.getTime();
		pret.setDateRetourThe(dateRetourThe);
		doc.setQuantite(doc.getQuantite()-1);}
		else {
			calendrier.add(Calendar.DATE, 20);
			dateRetourThe = calendrier.getTime();
			pret.setDateRetourThe(dateRetourThe);
			doc.setQuantite(doc.getQuantite()-1);}
		}
		
	

	@Override
	public void returnPret(Long numPret, Long codeAdh, Long idDoc) {
		Document doc=consulterUnDoc(idDoc);
		if(doc==null) throw new RuntimeException("document introuvable");
		Adherent adher=(Adherent) usersRepository.findOne(codeAdh); 
		if(adher==null) throw new RuntimeException("Adherent introuvable");
		Pret pret=consulterUnPret(numPret);
		pret.setDateRetourn(new Date());
		doc.setQuantite(doc.getQuantite()+1);
		
	}
	@Override
	public Date expirationDelaiPret(Date datePret,Long idAdherent) {
		Adherent adher=consulterAdherent(idAdherent); 
		if(adher==null) throw new RuntimeException("Adherent introuvable");
		Calendar calendar= new GregorianCalendar();
		calendar.setTime(datePret);
		if(adher instanceof Etudiant)
		{
			calendar.add(Calendar.DATE, 6);
		}
		else {
			calendar.add(Calendar.DATE, 20);	
			}
		return calendar.getTime();
	}
	@Override
	public Pret consulterUnPret(Long numPret) {
		Pret pret=pretRepository.findOne(numPret);
		return pret;
	}
	@Override
	public Page<Pret> listePret(int page, int size) {
		
		return pretRepository.listePret(new PageRequest(page, size));
	}
	@Override
	public Page<Pret> listePretAretourn(int page, int size) {
		
		return pretRepository.listePretAretourn(new PageRequest(page, size));
	}

	@Override
	public Page<Pret> listePretNoir(int page, int size) {

		return pretRepository.listePretNoir(new PageRequest(page, size));
	}
	@Override
	public Page<Pret> listePretNR(int page, int size) {

		return pretRepository.listePretNR(new PageRequest(page, size));
	}
	                     ////******* RESERVATIONS ********/////

	@Override
	public void reservation( Long idAdhe, Long idDoc) {
		
		Document doc=consulterUnDoc(idDoc);
		if(doc==null) throw new RuntimeException("document introuvable");
		Adherent user=(Adherent)usersRepository.findOne(idAdhe);
		if(user==null) throw new RuntimeException("utilisateur introuvable");
		doc.setQuantite(doc.getQuantite()-1);
		reservatioRepository.save(new Reservation(new Date(), doc, user));
	}
	public void annulReservation( Long idReserv, Long idDoc) {
		Document doc=consulterUnDoc(idDoc);
		if(doc==null) throw new RuntimeException("document introuvable");
		reservatioRepository.delete(idReserv);
		doc.setQuantite(doc.getQuantite()+1);
		
	}
	public void deletReservation( Long idReserv) {
		reservatioRepository.delete(idReserv);
		
	}
	public void validReservation(Long codeAdh, Long idDoc) {
		Document doc=consulterUnDoc(idDoc);
		if(doc==null) throw new RuntimeException("document introuvable");
		Adherent adher=(Adherent) usersRepository.findOne(codeAdh); 
		if(adher==null) throw new RuntimeException("Adherent introuvable");
		Pret pret=new Pret(new Date(), doc, adher);
		pretRepository.save(pret);
		Date dateRetourThe;
		Calendar calendrier = new GregorianCalendar();
		calendrier.setTime(new Date());
		if(adher instanceof Etudiant)
		{
		calendrier.add(Calendar.DATE, 6);
		dateRetourThe = calendrier.getTime();
		pret.setDateRetourThe(dateRetourThe);
		}
		else {
			calendrier.add(Calendar.DATE, 20);
			dateRetourThe = calendrier.getTime();
			pret.setDateRetourThe(dateRetourThe);
			doc.setQuantite(doc.getQuantite()-1);}
		}
		
	@Override
	public boolean expirationDelaiResev(Date dateRese) {
		Date d=new Date();
		Calendar calendar= new GregorianCalendar();
		calendar.setTime(dateRese);
		//pour demain
		calendar.add(Calendar.DATE, 1);
		if(d.equals(calendar.getTime())){
			return true;
		}
		return false;
	}
	
	
	@Override
	public Page<Reservation> listeReservation(int page, int size) {
		
		return reservatioRepository.listeReservation(new PageRequest(page,size));
	}

	
	
	//####################### GESTION DES DOCUMENTS ####################
	
	@Override
	public Document consulterUnDoc(Long idDoc) {
		Document doc=documentRepository.findOne(idDoc);
		if(doc==null) throw new RuntimeException("Document Introuvale");
		return doc;
	}
	public  TheseDoctorat consulterUnDoctorat(Long id){
		TheseDoctorat doctorat=(TheseDoctorat) documentRepository.findOne(id);
		return doctorat;
	}
	@Override
	public Categorie consulterCategorie(Long idCategorie) {
		Categorie cat=categorieRepository.findOne(idCategorie);
		if(cat==null) throw new RuntimeException("Categorie Introuvale");
		return cat;
	}
	@Override
	public void addCategorie(Categorie categorie) {
		categorieRepository.save(categorie);
		
	}
	

	@Override
	public void addLivre(Livre livre) {
		documentRepository.save(livre);
		
	}

	@Override
	public void addLivreToCategorie( Long idDoc,Long idCat) {
		Categorie cat=categorieRepository.findOne(idCat);
		if(cat==null) throw new RuntimeException("Categorie Introuvale");
		Document doc=documentRepository.findOne(idDoc);
		if(doc==null) throw new RuntimeException("Document Introuvale");
		doc.setCategorie(cat);
		
		
	}

	@Override
	public void addPFE(PFELicence pfe) {
		documentRepository.save(pfe);
		
	}

	@Override
	public void addMaster(MemoMaster master) {
		documentRepository.save(master);		
	}

	@Override
	public void addDoctorat(TheseDoctorat doctorat) {
		documentRepository.save(doctorat);		
	}


	@Override
	public void addMedia(Mediatheque mediatheque) {
		documentRepository.save(mediatheque);
	}

	@Override
	public Livre consulterUnLivre(Long isbn) {
		Livre lv=(Livre) documentRepository.findOne(isbn);
		return lv;
	}


	@Override
	public Page<Livre> consulterDesLivres(String mc,int page, int size) {
		
		return documentRepository.chercherLivres(mc ,new PageRequest(page, size));
	}
	
	@Override
	public Page<PFELicence> consultLicences(String mc,int page,int size) {
		
		return documentRepository.chercherPfeLicence(mc, new PageRequest(page, size));
	}
	public  PFELicence consulterUneLicence(Long id){
		PFELicence pfe=(PFELicence) documentRepository.findOne(id);
		return pfe;
	}
	public  MemoMaster consulterUnMemo(Long id){
		MemoMaster memo=(MemoMaster) documentRepository.findOne(id);
		return memo;
	}


	@Override
	public Page<MemoMaster> consultMasters(String mc,int page, int size) {
		
		return documentRepository.chercherMemoMaster(mc,new PageRequest(page, size));
	}



	@Override
	public Page<TheseDoctorat> consultDoctoras(String mc,int page, int size) {
		
		return documentRepository.chercherTheseDoctorat(mc, new PageRequest(page, size));
	}



	//################### GESTION DES ADHERENTS ############################
	@Override
	public Page<Adherent> listeAdherent(int page, int size) {
		
		return usersRepository.listeAdherent(new PageRequest(page, size));
	}
	@Override
	public Adherent consulterAdherent(Long idAdhe) {
//		Adherent adh=adherentRepository.findOne(idAdhe);
//		if(adh==null) throw new RuntimeException("Adhrent Introuvale");
		return null;
	}

	

}
