package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
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
 * The persistent class for the don database table.
 * 
 */
@Entity
@Table(name = "don", schema = "db_isophoto")
@NamedQuery(name = "Don.findAll", query = "SELECT d FROM Don d")
public class Don implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String commentaire;

	@Column(name = "\"dateDon\"")
	private Timestamp dateDon;

	private Integer montant;

	// bi-directional many-to-one association to Photo
	@ManyToOne
	@JoinColumn(name = "id_photo")
	@JsonBackReference(value="don")
	private Photo photo;

	public Don() {
	}

	public Don(String commentaire, Timestamp dateDon, Integer montant) {
		this.commentaire = commentaire;
		this.dateDon = dateDon;
		this.montant = montant;
	}

	public Don(String commentaire, Integer montant) {
		this.commentaire = commentaire;
		this.montant = montant;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Timestamp getDateDon() {
		return this.dateDon;
	}

	public void setDateDon(Timestamp dateDon) {
		this.dateDon = dateDon;
	}

	public Integer getMontant() {
		return this.montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}