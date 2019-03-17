package org.fsdm.web;


import java.io.File;
import java.io.IOException;


import javax.validation.Valid;


import org.fsdm.dao.DocumentRepository;
import org.fsdm.dao.PretRepository;
import org.fsdm.dao.UsersRepository;
import org.fsdm.entities.Adherent;
import org.fsdm.entities.Categorie;
import org.fsdm.entities.Document;
import org.fsdm.entities.Enseignant;
import org.fsdm.entities.Etudiant;
import org.fsdm.entities.Livre;
import org.fsdm.entities.Mediatheque;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.Pret;
import org.fsdm.entities.Reservation;
import org.fsdm.entities.RespBib;
import org.fsdm.entities.TheseDoctorat;
import org.fsdm.metier.IBibliothequeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/Admin")
public class BiblioController {
	@Autowired
	private IBibliothequeMetier bibliotheque;
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private UsersRepository usersRepository;
    @Autowired
	private DocumentRepository documentRepository;
	@Value("${dir.images}")
	private String imageDir;
	
	
	
	
	
	//####################### GESTION DES DOCUMENTS ####################
	
	@RequestMapping(value = "/")
	public String index(Model model,
			
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String motc) {
		
		Page<Pret> pret = bibliotheque.listePretNoir(0, 10);
		model.addAttribute("pagePrets",pret);

		Page<Pret> pretA = bibliotheque.listePretAretourn(0, 10);
		model.addAttribute("pagePretsA",pretA);

		Page<Reservation> rsersve = bibliotheque.listeReservation(0,10);
		model.addAttribute("pageReservations",rsersve);
		
		return "alertes";
	}

	@RequestMapping(value="/listeDocument")
	public String listdocument(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
	        @RequestParam(name="motCle",defaultValue="")String mc){
		
		Page<Document> docs=documentRepository.chercherDocuments("%"+mc+"%",new PageRequest(p, 10) );
		int pagesCount=docs.getTotalPages();
		int[] pages=new int[pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pageDocuments", docs);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "documentAdmin";
	}
	@RequestMapping(value="/supprimer/{id}")
	public String supprimerDocument(@PathVariable("id") Long id){
		documentRepository.delete(id);
		return "redirect:/Admin/listeDocument";
		
	}
	@RequestMapping("/listeLivres")
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
		 return "listeLivres";
	}
	@RequestMapping(value="/edit")
	public String eidt(Long id,Model model){
	Livre dc=bibliotheque.consulterUnLivre(id);
		model.addAttribute("livre",dc);
		return "EditDocument";
		
	}
	@RequestMapping(value="/UpdateDocument",method=RequestMethod.POST)
	public String update(@Valid Livre dc,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "EditDocument";
		}
		if(!(file.isEmpty())){dc.setPhoto(file.getOriginalFilename());}
		documentRepository.save(dc);
		if(!(file.isEmpty())){
			dc.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+dc.getNumDocum()));
		}
		return "redirect:/Admin/listeDocument";
	}
	@RequestMapping("/listePfeLicence")
	public String listePfe(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {
		
		Page<PFELicence> pfe=bibliotheque.consultLicences("%"+mc+"%", page, size);
			model.addAttribute("pagepfe",pfe);
			int pagesCount=pfe.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",mc);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		 return "listePfeLicence";
	}
	@RequestMapping(value="/editpfe")
	public String eidtpfe(Long id,Model model){
	PFELicence dc=bibliotheque.consulterUneLicence(id);
		model.addAttribute("pfe",dc);
		return "editpfe";
		
	}
	@RequestMapping(value="/Updatepfe",method=RequestMethod.POST)
	public String updatepfe(@Valid PFELicence dc,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "editpfe";
		}
		if(!(file.isEmpty())){dc.setPhoto(file.getOriginalFilename());}
		documentRepository.save(dc);
		if(!(file.isEmpty())){
			dc.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+dc.getNumDocum()));
		}
		return "redirect:/Admin/listePfeLicence";
	}
	@RequestMapping("/listeMaster")
	public String listeMaster(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {
		
		Page<MemoMaster> master=bibliotheque.consultMasters("%"+mc+"%", page, size);
			model.addAttribute("pagemaster",master);
			int pagesCount=master.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",mc);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		 return "listeMaster";
	}
	@RequestMapping(value="/editMaster")
	public String eidtMaster(Long id,Model model){
	MemoMaster dc=bibliotheque.consulterUnMemo(id);
		model.addAttribute("master",dc);
		return "editmaster";
		
	}
	@RequestMapping(value="/UpdateMaster",method=RequestMethod.POST)
	public String updateMaster(@Valid MemoMaster dc,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "editmaster";
		}
		if(!(file.isEmpty())){dc.setPhoto(file.getOriginalFilename());}
		documentRepository.save(dc);
		if(!(file.isEmpty())){
			dc.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+dc.getNumDocum()));
		}
		return "redirect:/Admin/listeMaster";
	}
	@RequestMapping("/listeThese")
	public String listeThese(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="motCle",defaultValue="")String mc
	       , @RequestParam(name="size",defaultValue="10")int size)
	        {
		model.addAttribute("motCle", mc);
		try {
		
		Page<TheseDoctorat> doctorat=bibliotheque.consultDoctoras("%"+mc+"%", page, size);
			model.addAttribute("pagedoctorat",doctorat);
			int pagesCount=doctorat.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
			model.addAttribute("motCle",mc);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		 return "listeThese";
	}
	@RequestMapping(value="/editThese")
	public String eidtThese(Long id,Model model){
	TheseDoctorat dc=bibliotheque.consulterUnDoctorat(id);
		model.addAttribute("doctorat",dc);
		return "editThese";
		
	}
	@RequestMapping(value="/UpdateThese",method=RequestMethod.POST)
	public String updateThese(@Valid TheseDoctorat dc,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "editThese";
		}
		if(!(file.isEmpty())){dc.setPhoto(file.getOriginalFilename());}
		documentRepository.save(dc);
		if(!(file.isEmpty())){
			dc.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+dc.getNumDocum()));
		}
		return "redirect:/Admin/listeThese";
	}	
	@RequestMapping(value="/Form",method=RequestMethod.GET)
	public String formDocument(Model model){
		model.addAttribute("document",new Document());
		return "FormDocument";
		
	}
	@RequestMapping(value="/SaveDocument",method=RequestMethod.POST)
	public String save(@Valid Document dc,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "FormDocument";
		}
		if(!(file.isEmpty())){dc.setPhoto(file.getOriginalFilename());}
		documentRepository.save(dc);
		if(!(file.isEmpty())){
			dc.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+dc.getNumDocum()));
		}
		return "FormDocument";
	}

	@RequestMapping(value="/AjoutLivre",method=RequestMethod.GET)
	public String formLivre(Model model){
	
		model.addAttribute("Livre",new Livre());
		return "FormLivre";
		
	}
	@RequestMapping(value="/SaveLivre",method=RequestMethod.POST)
	public String saveLivre(@Valid Livre lv,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors()){
			return "FormLivre";
		}
		if(!(file.isEmpty())){lv.setPhoto(file.getOriginalFilename());}
		bibliotheque.addLivre(lv);
		if(!(file.isEmpty())){
			lv.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+lv.getNumDocum()));
		}
		
		
		return "redirect:/Admin/";
		
	}
	//Ajouter un pfe Licence
		@RequestMapping(value = "/AjoutPFE", method = RequestMethod.GET)
		public String formPfe(Model model) {
			model.addAttribute("licence", new PFELicence());
			return "FormPfe";
		}
		@RequestMapping(value = "/SavePfe", method = RequestMethod.POST)
		public String savePfe(@Valid PFELicence pfe, BindingResult bindingResult,
				@RequestParam(name = "picture") MultipartFile file) throws IllegalStateException, IOException {
			if (bindingResult.hasErrors()) {
				return "FormPfe";
			}
			if (!(file.isEmpty())) {
				pfe.setPhoto(file.getOriginalFilename());
			}
			bibliotheque.addPFE(pfe);
			if (!(file.isEmpty())) {
				pfe.setPhoto(file.getOriginalFilename());
				file.transferTo(new File(imageDir + pfe.getNumDocum()));
			}
			return "redirect:/Admin/";
		}
		@RequestMapping(value = "/AjoutMemo", method = RequestMethod.GET)
		public String formMaster(Model model) {
			model.addAttribute("master", new MemoMaster());
			return "FormMaster";

		}

		@RequestMapping(value = "/SaveMaster", method = RequestMethod.POST)
		public String saveDocto(@Valid MemoMaster master, BindingResult bindingResult,
				@RequestParam(name = "picture") MultipartFile file) throws IllegalStateException, IOException {
			if (bindingResult.hasErrors()) {
				return "FormMemo";
			}
			if (!(file.isEmpty())) {
				master.setPhoto(file.getOriginalFilename());
			}
			bibliotheque.addMaster(master);;
			if (!(file.isEmpty())) {
				master.setPhoto(file.getOriginalFilename());
				file.transferTo(new File(imageDir + master.getNumDocum()));
			}
			return "redirect:/Admin/";
		}
		@RequestMapping(value = "/AjoutDoctorat", method = RequestMethod.GET)
		public String formDoctorat(Model model) {
			model.addAttribute("TheseDoctorat", new TheseDoctorat());
			return "FormDoctorat";

		}

		@RequestMapping(value = "/SaveDoctorat", method = RequestMethod.POST)
		public String saveDoctorat(@Valid TheseDoctorat doctorat, BindingResult bindingResult,
				@RequestParam(name = "picture") MultipartFile file) throws IllegalStateException, IOException {
			if (!(file.isEmpty())) {
				doctorat.setPhoto(file.getOriginalFilename());
			}

			bibliotheque.addDoctorat(doctorat);
			if (!(file.isEmpty())) {
				doctorat.setPhoto(file.getOriginalFilename());
				file.transferTo(new File(imageDir + doctorat.getNumDocum()));
			}

			return "redirect:/Admin/";
		}
		@RequestMapping(value = "/AjoutMedia", method = RequestMethod.GET)
		public String formMedia(Model model) {
			model.addAttribute("media", new Mediatheque());
			return "FormMedia";
		}
		@RequestMapping(value = "/SaveMedia", method = RequestMethod.POST)
		public String saveMedia(@Valid Mediatheque med, BindingResult bindingResult,
				@RequestParam(name = "picture") MultipartFile file) throws IllegalStateException, IOException {
			if (bindingResult.hasErrors()) {
				return "FormMedia";
			}
			if (!(file.isEmpty())) {
				med.setPhoto(file.getOriginalFilename());
			}
			bibliotheque.addMedia(med);
			if (!(file.isEmpty())) {
				med.setPhoto(file.getOriginalFilename());
				file.transferTo(new File(imageDir + med.getNumDocum()));
			}

			return "redirect:/Admin/";

		}

		
		@RequestMapping(value="/AjoutCategorie",method=RequestMethod.GET)
		public String formCategorie(Model model){
		
			model.addAttribute("Categorie",new Categorie());
			return "redirect:/Admin/";
			
		}
		@RequestMapping(value = "/SaveCategorie", method = RequestMethod.POST)
		public String saveCateorie(Categorie categorie) {
			bibliotheque.addCategorie(categorie);
			return "formCategorie";
		}
		//################### GESTION DES ADHERENTS ############################
		
	@RequestMapping(value="/adherent")
	public String Index(Model model){
		
		
		return "adherent";
	}

	@RequestMapping(value="/ListeAdherent",method=RequestMethod.GET)
	public String listrAdherents(Model model,
			@RequestParam(name="page",defaultValue="0")int p){
			Page<Adherent> adherents=bibliotheque.listeAdherent(0, 4);
			int pagesCount=adherents.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pageAdherents", adherents);
			model.addAttribute("pageCourante",p);
		    return "listeAdherent";
	}
	@RequestMapping(value = "/AjoutE", method = RequestMethod.GET)
	public String adh(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "ajoutE";
	}
	@RequestMapping(value = "/AjoutP", method = RequestMethod.GET)
	public String prof(Model model) {
		model.addAttribute("enseignant", new Enseignant());
		return "ajoutP";
	}
	@RequestMapping(value = "/AjoutA", method = RequestMethod.GET)
	public String resp(Model model) {
		model.addAttribute("Admin", new RespBib());
		return "ajoutA";
	}
	@RequestMapping(value = "/SaveE", method = RequestMethod.POST)
	public String saveadh(Etudiant etd) {
        usersRepository.save(etd);
		return "redirect:/Admin/";
	}
	@RequestMapping(value = "/SaveProf", method = RequestMethod.POST)
	public String saveProf(Enseignant etd) {
		usersRepository.save(etd);
		return "redirect:/Admin/";
	}
	@RequestMapping(value = "/SaveAdmin", method = RequestMethod.POST)
	public String saveresp(RespBib etd) {
		usersRepository.save(etd);
		return "redirect:/Admin/";
	}
	//#################### GSTION DES PRETS ET RESERVATIONS ####################
	
	@RequestMapping(value="/ListeReservation",method = RequestMethod.GET)
	public String listeReservation(Model model,@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size){
		try {
			Page<Reservation> reserves=bibliotheque.listeReservation(page,size);
			model.addAttribute("pageReservations",reserves);
			int[] pages=new int[reserves.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		 return "listeReservation";
	}
	@RequestMapping(value="/annuleReservation")
	public String supprimer(Long idDoc,Long idReserv){
		bibliotheque.annulReservation(idReserv, idDoc);
		return "redirect:/Admin/ListeReservation";
		
	}
	@RequestMapping(value="/validReservation",method = RequestMethod.GET)
	public String validerReservation(Long idDoc,Long codeAdh,Long idReserv){
		bibliotheque.validReservation(codeAdh, idDoc);
		bibliotheque.deletReservation(idReserv);
		return "redirect:/Admin/ListeReservation";
		
	}
	
	
	@RequestMapping(value="/FormPret",method = RequestMethod.GET)
	public String ajoutPret(Model model) {
	model.addAttribute("pret", new Pret());
		return "AjoutPret";
	}
	@RequestMapping(value = "/SavePret", method = RequestMethod.POST)
	public String savePret(@RequestParam("adherent.idAdherent")Long idAdh,@RequestParam("document.numDocum")Long idDoc) {
		bibliotheque.addPret(idAdh, idDoc);
		return "redirect:/Admin/";
	}
	@RequestMapping(value ="/ReturnPret",method = RequestMethod.GET)
	public String retournpret(Model model,Long numPret,Long codeAdh,Long idDoc) {
		try {
			bibliotheque.returnPret(numPret,codeAdh,idDoc);
		} catch (Exception e){
		model.addAttribute("exception", e);
		}
		
		return "redirect:/Admin/ListePretNR";
	}

	
	///Liste des Prets
	@RequestMapping(value="/ListePret",method=RequestMethod.GET)
	public String listrPret(Model model,
			@RequestParam(name="page",defaultValue="0")int p
			
			){
		    
			Page<Pret> pret=bibliotheque.listePret(0, 10);
			int pagesCount=pret.getTotalPages();
			int[] pages=new int[pagesCount];
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages",pages);
			model.addAttribute("pagePrets", pret);
			model.addAttribute("pageCourante",p);
		    return "listePret";
	}
	
	@RequestMapping(value = "/ListePretaRetourn", method = RequestMethod.GET)
	public String listPretaRetourn(Model model, @RequestParam(name = "page", defaultValue = "0") int p) {

		Page<Pret> pret = bibliotheque.listePretAretourn(0, 10);
		int pagesCount = pret.getTotalPages();
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("pagePrets", pret);
		model.addAttribute("pageCourante", p);
		return "listePretaRetourn";
	}
	@RequestMapping(value = "/ListePretNoir", method = RequestMethod.GET)
	public String listPretNoir(Model model, @RequestParam(name = "page", defaultValue = "0") int p) {

		Page<Pret> pret = bibliotheque.listePretNoir(0, 10);
		int pagesCount = pret.getTotalPages();
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("pagePrets", pret);
		model.addAttribute("pageCourante", p);
		return "listePretNoir";
	}
	@RequestMapping(value = "/ListePretNR", method = RequestMethod.GET)
	public String listPretNR(Model model, @RequestParam(name = "page", defaultValue = "0") int p) {

		Page<Pret> pret = bibliotheque.listePretNR(0, 10);
		int pagesCount = pret.getTotalPages();
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("pagePrets", pret);
		model.addAttribute("pageCourante", p);
		return "ListePretNR";
	}
	@RequestMapping(value = "/editPret/{id}", method = RequestMethod.GET)

	public String reserve(@PathVariable("id") Long id, Model model) {
		Pret pret = pretRepository.getOne(id);
		model.addAttribute("pret", pret);
		return "editPret";
	}
	
		

}
