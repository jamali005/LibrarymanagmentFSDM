//package org.fsdm.dao;
//
//import java.util.Date;
//import java.util.List;
//
//import org.fsdm.entities.Document;
//import org.fsdm.entities.Livre;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface DocumentRepository 
//extends JpaRepository<Document,Long>{
//	
//	
//	   @Query("select d from Document d where d.categorie.idCategorie=:x order by d.dateAcquisition desc")
//	   public Page<Document> listLivre(@Param("x")Long idCategori,Pageable pageable);
//}
package org.fsdm.dao;

import java.util.Date;
import java.util.List;

import org.fsdm.entities.Document;
import org.fsdm.entities.Livre;
import org.fsdm.entities.MemoMaster;
import org.fsdm.entities.PFELicence;
import org.fsdm.entities.TheseDoctorat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.fsdm.entities.Categorie;

public interface DocumentRepository 
    extends JpaRepository< Document , Long>{

    public  List<Document> findByCategorie(Categorie categorie);
    //spring data c'est le mapping objet relationnel
    
   
    @Query("select d from Document d where d.titre like :x ")
    public Page<Document> chercherDocuments(@Param("x")String mc,Pageable pageable);
//    
    //Livres
    @Query("select l from Livre l where l.titre like :x ")
    public Page<Livre> chercherLivres(@Param("x")String mc,Pageable pageable);
    
    //Licences
    @Query("select d from PFELicence d where d.titre like :x ")
    public Page<PFELicence> chercherPfeLicence(@Param("x")String mc,Pageable pageable);
    
    
   //Master 
    @Query("select d from MemoMaster d where d.titre like :x ")
    public Page<MemoMaster> chercherMemoMaster(@Param("x")String mc,Pageable pageable);
    
    //Doctorat
    @Query("select d from TheseDoctorat d where d.titre like :x ")
    public Page<TheseDoctorat> chercherTheseDoctorat(@Param("x")String mc,Pageable pageable);
    
    
  
    
}
