package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;

	private String email;

	@Column(name="id_authorities")
	private Integer idAuthorities;

	private String nom;

	private String password;

	private String prenom;

	private String pseudo;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="photographe")
	private List<Photo> photos;

	public Photographe() {
	}
	
	public Photographe(String email, 
					   String nom, 
					   String prenom, 
					   String pseudo,
					   String password) {
		
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.password = password;
		
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdAuthorities() {
		return this.idAuthorities;
	}

	public void setIdAuthorities(Integer idAuthorities) {
		this.idAuthorities = idAuthorities;
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

}