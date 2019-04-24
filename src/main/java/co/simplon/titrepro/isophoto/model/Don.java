package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the don database table.
 * 
 */
@Entity
@Table(name="don", schema = "db_isophoto")
@NamedQuery(name="Don.findAll", query="SELECT d FROM Don d")
public class Don implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String commentaire;

	@Column(name="\"dateDon\"")
	private Timestamp dateDon;

	private Integer montant;

	//bi-directional many-to-one association to Photo
	@ManyToOne
	@JoinColumn(name="id_photo")
	private Photo photo;

	public Don() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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