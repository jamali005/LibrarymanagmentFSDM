package org.fsdm.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MEDIA")
public class Mediatheque extends Document {

	private String typeDeMedia;

	public String getTypeDeMedia() {
		return typeDeMedia;
	}

	public void setTypeDeMedia(String typeDeMedia) {
		this.typeDeMedia = typeDeMedia;
	}

	public Mediatheque(){

	}

	public Mediatheque(Long numDocum, String titre, String etat, String photo, Date dateAcquisition, int quantite,
			String typeDeMedia) {
		super(numDocum, titre, etat, photo, dateAcquisition, quantite);
		this.typeDeMedia = typeDeMedia;
	}

	


}