package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@Table(name="adresse")
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="\"codePostal\"")
	private Integer codePostal;

	@Column(name="\"nomVoie\"", length=2147483647)
	private String nomVoie;

	@Column(name="\"numVoie\"")
	private Integer numVoie;

	@Column(length=2147483647)
	private String pays;

	@Column(length=2147483647)
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