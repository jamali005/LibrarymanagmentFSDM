package org.fsdm.dao;


import org.fsdm.entities.Adherent;
import org.fsdm.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long> {
	//  @Query("select type(U) from Users u where u.username= :x ")
//	  public String findRole(@Param("x")String mc);
	  Users findUserByUsername(String username);
	  @Query("select a from Adherent a")
	  public Page<Adherent> listeAdherent(Pageable pegeable);
}
