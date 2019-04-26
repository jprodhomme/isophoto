package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the commentaires database table.
 * 
 */
@Entity
@Table(name="commentaires", schema = "db_isophoto")
@NamedQuery(name="Commentaire.findAll", query="SELECT c FROM Commentaire c")
public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String commentaires;

	//bi-directional many-to-many association to Photo
	@ManyToMany(mappedBy="commentaires")
	@JsonIgnore
	private List<Photo> photos;

	

	public Commentaire() {
		
	}
	public Commentaire(String commentaireString) {
		this.commentaires = commentaireString;
	}
	
	public Commentaire(List<Photo> photos) {
		this.photos = photos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	

}