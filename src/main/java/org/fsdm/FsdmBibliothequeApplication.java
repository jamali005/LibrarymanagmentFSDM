package org.fsdm;

import java.util.Date;

import org.fsdm.dao.CategorieRepository;
import org.fsdm.dao.DocumentRepository;
import org.fsdm.dao.PretRepository;
import org.fsdm.dao.ReservatioRepository;
import org.fsdm.dao.RespBibRepository;
import org.fsdm.dao.UsersRepository;
import org.fsdm.entities.Livre;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.Pret;
import org.fsdm.entities.Reservation;
import org.fsdm.entities.RespBib;
import org.fsdm.entities.Users;
import org.fsdm.metier.IBibliothequeMetier;
import org.fsdm.entities.Adherent;
import org.fsdm.entities.Categorie;
import org.fsdm.entities.Document;
import org.fsdm.entities.Enseignant;
import org.fsdm.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class FsdmBibliothequeApplication implements CommandLineRunner {

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
	@Autowired
	private RespBibRepository respBibRepository;
	@Autowired
	private IBibliothequeMetier bibliothequeMetier;
	public static void main(String[] args) {
		SpringApplication.run(FsdmBibliothequeApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		
//		Adherent etudiant1=usersRepository.save(new Etudiant(1234567890L, "janati.mohamed@usmba.ac.ma", "123", true, "janati", "mohamed", "0679530400", "SMI","A","S6"));
//		Adherent etudiant2=usersRepository.save(new Etudiant(1234567891L, "janati.mohamed1@usmba.ac.ma", "123", true, "janati", "mohamed", "0679530400", "SMI","A","S6"));
//		Adherent enseignant1=usersRepository.save(new Enseignant(123456L,"mohamed.meknassi@usmba.ac.ma", "123", true, "Meknassi", "Mohammed", "0612458565", "Informatiques"));
//		RespBib respBib=usersRepository.save(new RespBib(142536L, "samira.benali@usmba.ac.ma", "123", true, "Benali", "Samira"));
//
//
//		Categorie c1=categorieRepository.save(new Categorie("MATHEMATIQUE ANALYSE", "P47"));
//		Categorie c2=categorieRepository.save(new Categorie("MATHEMATIQUE AlGEBRE", "P50"));
//		Categorie c3=categorieRepository.save(new Categorie("PHYSIQUE MATHEMATIQUE ", "P48"));
//		Categorie c4=categorieRepository.save(new Categorie("PHYSIQUE QUANTIQUE", "P59"));
//
//		Document livre1=documentRepository.save(new Livre(123187L,"Mathématique exercices et courrigé 1", "Bon", "photo",new Date(),2,1254L, "calvo driss", "Spring"));
//		Document livre2=documentRepository.save(new Livre(127577L,"Mathématique exercices et courrigé 2", "Bon", "photo",new Date(),2,1454L, "calvo driss", "Spring"));
//		Document livre3=documentRepository.save(new Livre(143087L,"Mathématique exercices et courrigé 3", "Bon", "photo",new Date(),2,1274L, "calvo driss", "Spring"));
//		Document livre4=documentRepository.save(new Livre(173887L,"Mathématique exercices et courrigé 4", "Bon", "photo",new Date(),2,1454L, "calvo driss", "Spring"));
//		Document livre5=documentRepository.save(new Livre(133587L,"Mathématique exercices et courrigé 5", "Bon", "photo",new Date(),2,1154L, "calvo driss", "Spring"));
//		Document livre6=documentRepository.save(new Livre(193587L,"Mathématique exercices et courrigé 6", "Bon", "photo",new Date(),2,1354L, "calvo driss", "Spring"));
//		
//		Document pfe1=documentRepository.save(new PFELicence(20171L, "Developpenet application jee spring", "bon", "photo", new Date(), 2, "2016/2017", "Informatique", "meknassi", "Mohammed janati ; Hatim jamali"));
//		Document pfe2=documentRepository.save(new PFELicence(20161L, "Developpenet application jee spring", "bon", "photo", new Date(), 2, "2016/2017", "Informatique", "meknassi", "Mohammed janati ; Hatim jamali"));
//		
//		
//		Document master1=documentRepository.save(new MemoMaster(20173L, "Reséeux et systèmes intélligents", "bon", "photo", new Date(), 2, "2016/2017", "meknassi", "Mohammed janati", "SIRM"));
//		Document master2=documentRepository.save(new MemoMaster(20173L, "Reséeux et systèmes intélligents", "bon", "photo", new Date(), 2, "2016/2017", "meknassi", "Mohammed janati", "SIRM"));
//		
//		bibliothequeMetier.addLivreToCategorie(123587L, 1L);
//	    bibliothequeMetier.reservation(1234567890L,123587L);
//	
	    ///#########################################################
//	    bibliothequeMetier.reservation(12356L, 127577L);
//	    	bibliothequeMetier.reservation(2623773475L, 1L);
//		Reservation r1=bibliothequeMetier.reservation(123456L, 1L);
//		bibliothequeMetier.reservation(123456L, 33L);
//		bibliothequeMetier.reservation(12256L, 1L);
//		bibliothequeMetier.addPret(2823773475L, 1L);
//		bibliothequeMetier.addPret(12276L, 1L);
		
////		Livre l1=bibliothequeMetier.consulterUnLivre(675912L);
////		System.out.println(l1.toString());
//		
//		Users u=new Users();
//		u=usersRepository.findUserByUsername("toto");
//		System.out.println(u.getPassword());
//		bibliothequeMetier.listeReservation(0, 4);
		
		
	}
}
