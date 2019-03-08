package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name = "photo")
@NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "\"aVendre\"")
	private Boolean aVendre;

	@Column(length = 2147483647)
	private String description;

	@Column(length = 2147483647)
	private String image;
	
	@Column
	private double prix;

	@Column(length = 2147483647)
	private String titre;

	// bi-directional many-to-many association to Commande
	@ManyToMany
	@JoinTable(name = "many_photo_has_many_commande", joinColumns = {
			@JoinColumn(name = "id_photo", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_commande", nullable = false) })
	private List<Commande> commandes;

	// bi-directional many-to-many association to Timeline
	@ManyToMany(mappedBy = "photos")
	private List<Timeline> timelines;

	// bi-directional many-to-one association to Categorie
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;

	// bi-directional many-to-one association to Exif
	@ManyToOne
	@JoinColumn(name = "id_exif")
	private Exif exif;

	// bi-directional many-to-one association to Photographe
	@ManyToOne
	@JoinColumn(name = "id_photographe")
	private Photographe photographe;

	public Photo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAVendre() {
		return this.aVendre;
	}

	public void setAVendre(Boolean aVendre) {
		this.aVendre = aVendre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Timeline> getTimelines() {
		return this.timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Exif getExif() {
		return this.exif;
	}

	public void setExif(Exif exif) {
		this.exif = exif;
	}

	public Photographe getPhotographe() {
		return this.photographe;
	}

	public void setPhotographe(Photographe photographe) {
		this.photographe = photographe;
	}

}