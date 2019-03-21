package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the timeline database table.
 * 
 */
@Entity
@Table(name = "timeline", schema= "db_isophoto")
@NamedQuery(name="Timeline.findAll", query="SELECT t FROM Timeline t")
public class Timeline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dateCreation\"")
	private Date dateCreation;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dateUpdate\"")
	private Date dateUpdate;

	private String nom;

	//bi-directional many-to-many association to Photo
	@ManyToMany
	@JoinTable(
		name="many_timeline_has_many_photo"
		, joinColumns={
			@JoinColumn(name="id_timeline")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_photo")
			}
		, schema = "db_isophoto")
	private List<Photo> photos;

	//bi-directional many-to-one association to Photographe
	@ManyToOne
	@JoinColumn(name="id_photographe")
	private Photographe photographe;

	public Timeline() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
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

	public Photographe getPhotographe() {
		return this.photographe;
	}

	public void setPhotographe(Photographe photographe) {
		this.photographe = photographe;
	}

}