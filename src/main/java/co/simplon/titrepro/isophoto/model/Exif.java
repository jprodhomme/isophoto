package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the exif database table.
 * 
 */
@Entity
@Table(name = "exif", schema = "db_isophoto")
@NamedQuery(name = "Exif.findAll", query = "SELECT e FROM Exif e")
public class Exif implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;

	private String constructeur;

	@Column(name = "\"dateHeure\"")
	private Timestamp dateHeure;

	private Boolean flash;

	private String modele;

	private String objectif;

	private String ouverture;

	@Column(name = "\"vitesseObturation\"")
	private double vitesseObturation;

	// bi-directional many-to-one association to Photo
	@OneToMany(mappedBy = "exif")
	private List<Photo> photos;

	public Exif() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConstructeur() {
		return this.constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

	public Timestamp getDateHeure() {
		return this.dateHeure;
	}

	public void setDateHeure(Timestamp dateHeure) {
		this.dateHeure = dateHeure;
	}

	public Boolean getFlash() {
		return this.flash;
	}

	public void setFlash(Boolean flash) {
		this.flash = flash;
	}

	public String getModele() {
		return this.modele;
	}

	public void String(String modele) {
		this.modele = modele;
	}

	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getOuverture() {
		return this.ouverture;
	}

	public void setOuverture(String ouverture) {
		this.ouverture = ouverture;
	}

	public double getVitesseObturation() {
		return this.vitesseObturation;
	}

	public void setVitesseObturation(double vitesseObturation) {
		this.vitesseObturation = vitesseObturation;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setExif(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setExif(null);

		return photo;
	}

}