package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private Integer id;

	private String commentaires;

	//bi-directional many-to-many association to Photo
	@ManyToMany(mappedBy="commentaires")
	private List<Photo> photos;

	public Commentaire() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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