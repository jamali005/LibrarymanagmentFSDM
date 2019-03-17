package org.fsdm.dao;

import org.fsdm.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ReservatioRepository 
extends JpaRepository<Reservation, Long>{

	@Query("select r from Reservation r order by r.dateReservation desc ")
    public Page<Reservation> listeReservation(Pageable pageable);
	

}
