//package org.fsdm.web;
//
//import java.util.List;
//
//import org.fsdm.dao.AdherentRepository;
//import org.fsdm.dao.DocumentRepository;
//import org.fsdm.entities.Adherent;
//import org.fsdm.entities.Categorie;
//import org.fsdm.entities.Etudiant;
//import org.fsdm.entities.Livre;
//import org.fsdm.entities.Document;
//import org.fsdm.metier.IBibliothequeMetier;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class BibliothequeController {
//	@Autowired
//	private IBibliothequeMetier bibliothequeMetier;
//	@Autowired
//	private AdherentRepository adherentRepository;
//	@Autowired
//	private DocumentRepository documentRepository;
//
////	@RequestMapping(value = "/adherent", method = RequestMethod.GET)
////	public String adh(Model model) {
////
////		model.addAttribute("etudiant", new Etudiant());
////
////		return "adherent";
////	}
////
////	@RequestMapping(value = "/SaveAdherent", method = RequestMethod.POST)
////	public String saveadh(Etudiant etd) {
////		adherentRepository.save(etd);
////
////		return "adherent";
////	}
//
//	@RequestMapping("/documents")
//	public String index(Model model,Long idCategorie) {
//		try {
//			Categorie cat=bibliothequeMetier.consulterCategorie(idCategorie);
//			Page<Document> pageLivre=bibliothequeMetier.listLivre(1L,0,4);
//			model.addAttribute("categorie",cat);
//	 		model.addAttribute("listLivres",pageLivre.getContent());
//		} catch (Exception e) {
//			model.addAttribute("exeption", e);
//		}		
//		return "documents";
//	}
//
//	@RequestMapping("/livres")
//	public String consultLivreParCategorie(Model model, String typeDoc) {
////		List<Livre> lvs = bibliothequeMetier.consulterDesLivres();
////		model.addAttribute("livres", lvs);
//
//		return "livres";
//	}
//
//	@RequestMapping("/pfelicences")
//	public String consultLicences(Model model, String typeDoc) {
//
//		return "pfelicences";
//	}
//
//	@RequestMapping("/memoiresmaster")
//	public String consultMasters(Model model, String typeDoc) {
//
//		return "masters";
//	}
//
//	@RequestMapping("/thesesdoctorats")
//	public String consultDoctoras(Model model, String typeDoc) {
//
//		return "doctoras";
//	}
//
////	@RequestMapping("/consulterLivre")
////	public String consulter(Model model, Long idDoc) {
////		try {
////			Document doc = (Document) bibliothequeMetier.consulterUnDoc(idDoc);
////			model.addAttribute("Document", doc);
////		} catch (Exception e) {
////			model.addAttribute("exeption", e);
////		}
////
////		return "documents";
////	}
//}
package org.fsdm.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.fsdm.dao.DocumentRepository;
import org.fsdm.entities.Document;
import org.fsdm.entities.Livre;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.Reservation;
import org.fsdm.entities.TheseDoctorat;
import org.fsdm.metier.IBibliothequeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/Documents")
public class BibliothequeController {
	@Autowired
	private IBibliothequeMetier bibliotheque;
	@Autowired  
      private DocumentRepository documentRepository;
	@Value("${dir.images}")
	private String imageDir;
	
	
	@RequestMapping(value="/Index")
	public String Index(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String motc){
			Page<Livre> livres=bibliotheque.consulterDesLivres("%"+motc+"%", page, 4);
			model.addAttribute("pageLivres",livres);
			Page<PFELicence> licences=bibliotheque.consultLicences("%"+motc+"%", page, 4);
			model.addAttribute("pageLicences",licences);
			Page<MemoMaster> masters=bibliotheque.consultMasters("%"+motc+"%", page, 4);
			model.addAttribute("pageMasters",masters);
			Page<TheseDoctorat> doctoras=bibliotheque.consultDoctoras("%"+motc+"%", page, 4);
			model.addAttribute("pageDoctoras",doctoras);
			
		return "documents";
	}

	@RequestMapping(value="/getPhoto",
			produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws IOException{
		File f=new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
		
	}
	
@RequestMapping(value="/reserve",method=RequestMethod.GET)
	public String reserve(Long id,Model model){
		try {
			    Document dc=documentRepository.getOne(id);
				model.addAttribute("document",dc);
				model.addAttribute("Reservation",new Reservation());
		} catch (Exception e) {
			model.addAttribute("exception",e);
			
		}
		return "Reservation";
	}
	@RequestMapping(value="/SaveReservation",method=RequestMethod.POST)
	public String reservation(Model model,@RequestParam("numDocum") Long dc,@RequestParam("adherent.idAdherent")Long idAdh){
		try {
			
			bibliotheque.reservation(idAdh,dc);	
		} catch (Exception e) {
			model.addAttribute("exception",e);
		
		}
			
		
		return "redirect:/Documents/Index";
		
	}
	@RequestMapping("/livres")
	public String listeLivres(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {
		
		Page<Livre> livres=bibliotheque.consulterDesLivres("%"+mc+"%", page, size);
			model.addAttribute("pageLivres",livres);

			int pagesCount=livres.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",mc);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		 return "livres";
	}
	
	@RequestMapping("/pfelicences")
	public String consultLicences(Model model, 
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {
		Page<PFELicence> licences=bibliotheque.consultLicences("%"+mc+"%", page, size);
			model.addAttribute("pageLicences",licences);
			int pagesCount=licences.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}

		return "pfelicences";
	}

	@RequestMapping("/memoiresmaster")
	public String consultMasters(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {

		Page<MemoMaster> masters=bibliotheque.consultMasters("%"+mc+"%", page, size);
			model.addAttribute("pageMasters",masters);
			int pagesCount=masters.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}

		return "masters";
	}

	@RequestMapping("/thesesdoctorats")
	public String consultDoctoras(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {

		Page<TheseDoctorat> doctoras=bibliotheque.consultDoctoras("%"+mc+"%", page, size);
			model.addAttribute("pageDoctoras",doctoras);
			int pagesCount=doctoras.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}

		return "doctoras";
	}

	
}



