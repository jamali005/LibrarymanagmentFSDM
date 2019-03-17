package org.fsdm.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE", discriminatorType = DiscriminatorType.STRING, length = 6)
public class Users implements Serializable {
	@Id
	private Long idAdherent;
	private String username;
	private String password;
	private boolean actived=true;

	public String getUsername() {
		return username;
	}

	public Long getIdAdherent() {
		return idAdherent;
	}

	public void setIdAdherent(Long idAdherent) {
		this.idAdherent = idAdherent;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Users(Long idAdherent, String username, String password, boolean actived) {
		super();
		this.idAdherent = idAdherent;
		this.username = username;
		this.password = password;
		this.actived = actived;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

}
