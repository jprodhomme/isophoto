package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the photographe database table.
 * 
 */
@Entity
@Table(name = "photographe")
@NamedQuery(name = "Photographe.findAll", query = "SELECT p FROM Photographe p")
public class Photographe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 2147483647)
	private String email;

	@Column(name = "id_authorities")
	private Integer idAuthorities;

	@Column(length = 2147483647)
	private String nom;

	@Column(length = 2147483647)
	private String password;

	@Column(length = 2147483647)
	private String prenom;

	@Column(length = 2147483647)
	private String pseudo;

	// bi-directional many-to-one association to Commande
	@OneToMany(mappedBy = "photographe")
	private List<Commande> commandes;

	// bi-directional many-to-many association to Adresse
	@ManyToMany
	@JoinTable(name = "many_photographe_has_many_adresse", joinColumns = {
			@JoinColumn(name = "id_photographe", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_adresse", nullable = false) })
	private List<Adresse> adresses;

	// bi-directional many-to-one association to Photo
	@OneToMany(mappedBy = "photographe")
	private List<Photo> photos;

	// bi-directional many-to-one association to Timeline
	@OneToMany(mappedBy = "photographe")
	private List<Timeline> timelines;

	public Photographe() {
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

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setPhotographe(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setPhotographe(null);

		return commande;
	}

	public List<Adresse> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
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

	public List<Timeline> getTimelines() {
		return this.timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}

	public Timeline addTimeline(Timeline timeline) {
		getTimelines().add(timeline);
		timeline.setPhotographe(this);

		return timeline;
	}

	public Timeline removeTimeline(Timeline timeline) {
		getTimelines().remove(timeline);
		timeline.setPhotographe(null);

		return timeline;
	}

}