package org.fsdm.dao;

import org.fsdm.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository 
extends JpaRepository<Categorie, Long>{

}
