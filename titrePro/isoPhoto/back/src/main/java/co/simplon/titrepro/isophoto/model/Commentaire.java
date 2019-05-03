package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the commentaires database table.
 * 
 */
@Entity
@Table(name = "commentaires", schema = "db_isophoto")
@NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c")
public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String commentaires;

	// bi-directional many-to-one association to Photo
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id_photo")
	private Photo photo;

	public Commentaire() {
	}

	public Commentaire(String commentaires) {
		this.commentaires = commentaires;
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

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}