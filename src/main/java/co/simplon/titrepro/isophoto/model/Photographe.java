package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the photographe database table.
 * 
 */
@Entity
@Table(name="photographe", schema = "db_isophoto")
@NamedQuery(name="Photographe.findAll", query="SELECT p FROM Photographe p")
public class Photographe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String nom;

	private String password;

	private String prenom;

	private String pseudo;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="photographe")
	private List<Photo> photos;

	//bi-directional many-to-one association to Authority
	@ManyToOne
	@JoinColumn(name="id_authorities")
	private Authority authority;

	public Photographe() {
	}

	public Photographe(String email,
					   String nom, 
					   String password,
					   String prenom, 
					   String pseudo
					   ) {
		this.email = email;
		this.nom = nom;
		this.password = password;
		this.prenom = prenom;
		this.pseudo = pseudo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	@JsonIgnore
	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setPhotographe(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setPhotographe(null);

		return photo;
	}
	@JsonIgnore
	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	
}