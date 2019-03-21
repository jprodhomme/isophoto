package co.simplon.titrepro.isophoto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@Table(name = "utilisateur", schema= "db_isophoto")
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	//bi-directional many-to-one association to Authority
	@ManyToOne
	@JoinColumn(name="id_authorities")
	private Authority authority;

	public Utilisateur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}