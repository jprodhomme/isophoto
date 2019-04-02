package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@Table(name = "adresse", schema= "db_isophoto")
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;

	@Column(name="\"codePostal\"")
	private Integer codePostal;

	@Column(name="\"nomVoie\"")
	private String nomVoie;

	@Column(name="\"numVoie\"")
	private Integer numVoie;

	private String pays;

	private String ville;

	//bi-directional many-to-many association to Photographe
	@ManyToMany(mappedBy="adresses")
	private List<Photographe> photographes;

	public Adresse() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomVoie() {
		return this.nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public Integer getNumVoie() {
		return this.numVoie;
	}

	public void setNumVoie(Integer numVoie) {
		this.numVoie = numVoie;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Photographe> getPhotographes() {
		return this.photographes;
	}

	public void setPhotographes(List<Photographe> photographes) {
		this.photographes = photographes;
	}

}