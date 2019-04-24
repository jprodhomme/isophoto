package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.titrepro.isophoto.services.PhotographeService;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name="photo", schema = "db_isophoto")
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private String image;

	private String titre;

	//bi-directional many-to-one association to Don
	@OneToMany(mappedBy="photo")
	private List<Don> dons;

	//bi-directional many-to-many association to Commentaire
	@ManyToMany
	@JoinTable(
		name="many_commentaires_has_many_photo"
		, joinColumns={
			@JoinColumn(name="id_photo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_commentaires")
			}
		)
	private List<Commentaire> commentaires;

	//bi-directional many-to-many association to Tag
	@ManyToMany(mappedBy="photos")
	private List<Tag> tags;

	//bi-directional many-to-one association to Photographe
	@ManyToOne
	@JoinColumn(name="id_photographe")
	private Photographe photographe;

	public Photo() {
		
	}
	
	public Photo(String description, 
				 String image, 
				 String titre, 
				 ArrayList<Tag> tags, 
				 Photographe photographe) {
		this.description = description;
		this.titre = titre;
		this.image = image;
		this.tags = tags;
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
	@JsonIgnore
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
	@JsonIgnore
	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	@JsonIgnore
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