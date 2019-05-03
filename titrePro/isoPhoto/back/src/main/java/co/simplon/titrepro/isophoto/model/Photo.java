package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private Long id;

	private String description;

	private String image;

	private String titre;

	// bi-directional many-to-one association to Don
	@OneToMany(mappedBy = "photo")
	private List<Don> dons;

	// bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy = "photo")
	@JsonManagedReference
	private List<Commentaire> commentaires;

	// bi-directional many-to-many association to Tag
	@ManyToMany
	@JoinTable(name = "many_tags_has_many_photo", schema = "db_isophoto", joinColumns = {
			@JoinColumn(name = "id_tags") }, inverseJoinColumns = { @JoinColumn(name = "id_photo") })
	private List<Tag> tags;

	// bi-directional many-to-one association to Photographe
	@ManyToOne
	@JoinColumn(name = "id_photographe")
	private Photographe photographe;

	public Photo() {

	}

	public Photo(String description, String titre, String image, ArrayList<Tag> tagsString, Photographe photographe) {
		this.description = description;
		this.titre = titre;
		this.image = image;
		this.tags = tagsString;
		this.photographe = photographe;

	}

	public Photo(String description, String image, String titre, Photographe photographe) {
		this.description = description;
		this.titre = titre;
		this.image = image;
		this.photographe = photographe;

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public Don addDon(Don don) {
		getDons().add(don);
		don.setPhoto(this);

		return don;
	}

	public Don removeDon(Don don) {
		getDons().remove(don);
		don.setPhoto(null);

		return don;
	}

	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Photographe getPhotographe() {
		return this.photographe;
	}

	public void setPhotographe(Photographe photographe) {
		this.photographe = photographe;
	}

}