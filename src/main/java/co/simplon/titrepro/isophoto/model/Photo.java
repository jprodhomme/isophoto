package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name = "photo", schema = "db_isophoto")
@NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;

	@Column(name = "\"aVendre\"")
	private Boolean aVendre;

	private String description;

	private String image;

	private double prix;

	private String titre;

	// bi-directional many-to-many association to Commande
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "many_photo_has_many_commande", joinColumns = {
			@JoinColumn(name = "id_photo") }, inverseJoinColumns = {
					@JoinColumn(name = "id_commande") }, schema = "db_isophoto")
	private List<Commande> commandes;

	// bi-directional many-to-many association to Timeline
	@ManyToMany(mappedBy = "photos")
	@JsonIgnore
	private List<Timeline> timelines;

	// bi-directional many-to-one association to Categorie
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;

	// bi-directional many-to-one association to Exif
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_exif")
	private Exif exif;

	// bi-directional many-to-one association to Photographe
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_photographe")
	private Photographe photographe;

	public Photo() {

	}

	public Photo(boolean aVendre, String description, String image, float prix, String titre) {
		super();
		this.aVendre = aVendre;
		this.description = description;
		this.image = image;
		this.prix = prix;
		this.titre = titre;
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

	@Override
	public String toString() {
		return "Photo [id=" + id + ", aVendre=" + aVendre + ", description=" + description + ", image=" + image
				+ ", prix=" + prix + ", titre=" + titre + ", commandes=" + commandes + ", timelines=" + timelines
				+ ", categorie=" + categorie + ", exif=" + exif + ", photographe=" + photographe + "]";
	}

}