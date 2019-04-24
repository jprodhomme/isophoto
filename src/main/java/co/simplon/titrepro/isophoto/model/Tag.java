package co.simplon.titrepro.isophoto.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags", schema = "db_isophoto")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String tag;

	//bi-directional many-to-many association to Photo
	@ManyToMany
	@JoinTable(
		name="many_tags_has_many_photo"
		, joinColumns={
			@JoinColumn(name="id_tags")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_photo")
			}
		)
	private List<Photo> photos;

	public Tag() {
	}
	
	public Tag(String tag) {
		this.tag = tag;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}