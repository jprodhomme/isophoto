package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(name = "authorities", schema = "db_isophoto")
@NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String role;

	// bi-directional many-to-one association to Photographe
	@OneToMany(mappedBy = "authority")
	@JsonManagedReference
	private List<Photographe> photographes;

	public Authority() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Photographe> getPhotographes() {
		return this.photographes;
	}

	public void setPhotographes(List<Photographe> photographes) {
		this.photographes = photographes;
	}

	public Photographe addPhotographe(Photographe photographe) {
		getPhotographes().add(photographe);
		photographe.setAuthority(this);

		return photographe;
	}

	public Photographe removePhotographe(Photographe photographe) {
		getPhotographes().remove(photographe);
		photographe.setAuthority(null);

		return photographe;
	}

}