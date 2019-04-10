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
import javax.persistence.OneToMany;
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

	private String description;

	private String image;

	private String titre;

	//bi-directional many-to-one association to Don
	@OneToMany(mappedBy="photo")
	private List<Don> dons;

	//bi-directional many-to-many association to Categorie
	@ManyToMany
	@JoinTable(
		name="many_photo_has_many_categorie"
		, joinColumns={
			@JoinColumn(name="id_photo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_categorie")
			}
		)
	private List<Categorie> categories;

	//bi-directional many-to-one association to Photographe
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_photographe")
	private Photographe photographe;

	public Photo() {
	}
	
	public Photo(String description,
				 String image,
				 String categorie,
				 String titre) {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Don> getDons() {
		return this.dons;
	}

	public void setDons(List<Don> dons) {
		this.dons = dons;
	}

	public List<Categorie> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public Photographe getPhotographe() {
		return this.photographe;
	}

	public void setPhotographe(Photographe photographe) {
		this.photographe = photographe;
	}

}