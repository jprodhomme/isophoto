package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;

import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@Table(name = "categorie", schema= "db_isophoto")
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nom;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="categorie")
	private List<Photo> photos;

	public Categorie() {
	}

	public Categorie(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setCategorie(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setCategorie(null);

		return photo;
	}

}