package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name = "commande", schema= "db_isophoto")
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="\"dateCommande\"")
	private Timestamp dateCommande;

	@Column(name="\"fraisDePort\"")
	private double fraisDePort;

	private Integer quantité;

	private double ttc;

	private double tva;

	//bi-directional many-to-one association to Photographe
	@ManyToOne
	@JoinColumn(name="id_photographe")
	private Photographe photographe;

	//bi-directional many-to-many association to Photo
	@ManyToMany(mappedBy="commandes")
	private List<Photo> photos;

	public Commande() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Timestamp dateCommande) {
		this.dateCommande = dateCommande;
	}

	public double getFraisDePort() {
		return this.fraisDePort;
	}

	public void setFraisDePort(double fraisDePort) {
		this.fraisDePort = fraisDePort;
	}

	public Integer getQuantité() {
		return this.quantité;
	}

	public void setQuantité(Integer quantité) {
		this.quantité = quantité;
	}

	public double getTtc() {
		return this.ttc;
	}

	public void setTtc(double ttc) {
		this.ttc = ttc;
	}

	public double getTva() {
		return this.tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public Photographe getPhotographe() {
		return this.photographe;
	}

	public void setPhotographe(Photographe photographe) {
		this.photographe = photographe;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}