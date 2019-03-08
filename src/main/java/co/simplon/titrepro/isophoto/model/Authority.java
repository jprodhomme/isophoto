package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(name="authorities")
@NamedQuery(name="Authority.findAll", query="SELECT a FROM Authority a")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String role;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="authority")
	private List<Utilisateur> utilisateurs;

	public Authority() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setAuthority(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setAuthority(null);

		return utilisateur;
	}

}