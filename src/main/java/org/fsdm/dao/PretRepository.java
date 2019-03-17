package org.fsdm.dao;



import org.fsdm.entities.Pret;
import org.fsdm.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository 
extends JpaRepository<Pret, Long> {
	
	@Query("select p from Pret p where p.dateRetourn is not null ")
    public Page<Pret> listePret(Pageable pageable);
	@Query("select p from Pret p where year(p.dateRetourThe) = year(sysdate()) and month(p.dateRetourThe) = month(sysdate()) and day(p.dateRetourThe) = day(sysdate()) ")
    public Page<Pret> listePretAretourn(Pageable pageable);
	@Query("select p from Pret p where p.dateRetourThe < sysdate() and p.dateRetourn is null")
    public Page<Pret> listePretNoir(Pageable pageable);
	@Query("select p from Pret p where p.dateRetourThe > sysdate() and p.dateRetourn is null")
    public Page<Pret> listePretNR(Pageable pageable);
}
