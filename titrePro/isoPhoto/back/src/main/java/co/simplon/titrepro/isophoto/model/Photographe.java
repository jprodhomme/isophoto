package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the photographe database table.
 * 
 */
@Entity
@Table(name = "photographe", schema = "db_isophoto")
@NamedQuery(name = "Photographe.findAll", query = "SELECT p FROM Photographe p")
public class Photographe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;

	private String prenom;

	private String pseudo;

	private String email;

	private String password;

	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="photographe")
	@JsonIgnore
	private List<Commentaire> commentaires;

	// bi-directional many-to-one association to Photo
	@JsonIgnore
	@OneToMany(mappedBy = "photographe", cascade = CascadeType.REMOVE)
	private List<Photo> photos;


	// bi-directional many-to-one association to Authority
	@ManyToOne
	@JoinColumn(name = "id_authorities")
	private Authority authority;


	public Photographe() {
	}

	public Photographe(String nom, String prenom, String pseudo, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}

	public Photographe(String nom, String prenom, String pseudo,String email, String password, Authority authority) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}
	
	public Photographe(String pseudo, String password) {
		this.pseudo = pseudo;
		this.password = password;
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
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}